package com.iot.nero.smartcan.server;


import com.iot.nero.smartcan.SmartCanBootstrap;
import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.exceptions.PackageBrokenException;
import com.iot.nero.smartcan.factory.ConfigFactory;
import com.iot.nero.smartcan.factory.ServiceFactory;
import com.iot.nero.smartcan.spi.OnMessageReceivedListener;
import com.iot.nero.smartcan.utils.classandjar.JarUtils;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/5
 * Time   7:35 PM
 */

public class WorkerServeHandler extends ServerHandler {

    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static final int PROCESSING = 2;

    Protocol protocol;
    Object responseData;
    Object requestData;

    public WorkerServeHandler(SocketChannel socketChannel, Selector selector) throws IOException {
        super(socketChannel, selector);
    }

    @Override
    public synchronized void read() throws IOException {
        int readCount = socketChannel.read(this.input);
        if (readCount > 0) {
            this.state = PROCESSING;
            executorService.execute(new Processor(readCount));
        }
        this.selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    synchronized void processAndHandle(int readCount) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, PackageBrokenException {
        this.readBytes(readCount);
        this.state = SENDING;
        this.selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    @Override
    public synchronized void readProcess() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, PackageBrokenException {
        CONSTANT.pInfo("(READ)" + receivedBytes.toString());
        protocol = new Protocol().decode(receivedBytes);
    }


    @Override
    public synchronized void writeProcess() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Object response;
        Class<?> clz = Class.forName("com.iot.nero.smartcan.service.impl.ProtocolService");
        FastClass fastClass = FastClass.create(clz);
        try {

            FastMethod fastMethod = fastClass.getMethod(
                    SmartCanBootstrap.autoBrainServiceMap.get(protocol.getCommandUnit()[0]).getName(),
                    SmartCanBootstrap.autoBrainServiceMap.get(protocol.getCommandUnit()[0]).getParameterTypes());

            ServiceLoader<OnMessageReceivedListener> messageReceivedListenerServiceLoader = ServiceLoader.load(OnMessageReceivedListener.class);

            // 调用 SPI
            for (OnMessageReceivedListener onMessageReceivedListener : messageReceivedListenerServiceLoader) {
                onMessageReceivedListener.OnMessageReceived(protocol);
            }

            File file = new File(System.getProperty("user.dir") + "/" + ConfigFactory.getConfig().getPluginPath());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File plugin : files) {
                    if (plugin.isFile() && plugin.getName().endsWith("jar")) {
                        Class<?> receivedClass = JarUtils.getClass(plugin.getAbsolutePath(),
                                "com.iot.nero.smartcan.plugin.impl.MessageReceivedListener"
                        );

                        Method[] method = receivedClass.getMethods();

                        for (Method m : method) {
                            if (m.getName().contains("OnMessageReceived")) {
                                m.invoke(receivedClass.newInstance(), protocol);
                            }
                        }
                    }
                }
            }

            fastMethod.invoke(ServiceFactory.getService(fastClass), new Object[]{protocol, socketChannel});

        } catch (NullPointerException e) {
            this.selectionKey.interestOps(SelectionKey.OP_READ);
            this.state = READING;
        }
    }


    class Processor implements Runnable {
        int readCount;

        public Processor(int readCount) {
            this.readCount = readCount;
        }

        public synchronized void run() {
            try {
                processAndHandle(readCount);
            } catch (IOException | IllegalAccessException | ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                System.out.println(e.getMessage());
                input.clear();
                state = READING;
                selectionKey.interestOps(SelectionKey.OP_READ);
            } catch (PackageBrokenException e) {
                System.out.println(e.getMessage());
                input.clear();
                state = READING;
                selectionKey.interestOps(SelectionKey.OP_READ);
            }
        }
    }

}
