package com.iot.nero.smartcan.server;


import com.iot.nero.smartcan.ABBootstrap;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.exceptions.PackageBrokenException;
import com.iot.nero.smartcan.constant.CONSTANT;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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
    public void read() throws IOException {
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
    public void writeProcess() throws IOException, ClassNotFoundException, InvocationTargetException {

        Object response;
        Class<?> clz = Class.forName("com.iot.nero.middleware.dfs.index.service.impl.ProtocolService");
        FastClass fastClass = FastClass.create(clz);
        try {
            FastMethod fastMethod = fastClass.getMethod(
                    ABBootstrap.autoBrainServiceMap.get(protocol.getCommandUnit()[0]).getName(),
                    ABBootstrap.autoBrainServiceMap.get(protocol.getCommandUnit()[0]).getParameterTypes());

            fastMethod.invoke(fastClass.newInstance(), new Object[]{protocol, socketChannel});

        }catch (NullPointerException e){
            this.selectionKey.interestOps(SelectionKey.OP_READ);
            this.state = READING;
        }
    }


    class Processor implements Runnable {
        int readCount;

        public Processor(int readCount) {
            this.readCount = readCount;
        }

        public void run() {
            try {
                processAndHandle(readCount);
            } catch (IOException | IllegalAccessException | ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (PackageBrokenException e) {
                e.printStackTrace();
            }
        }
    }

}