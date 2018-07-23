package com.iot.nero.smartcan.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import static com.iot.nero.smartcan.constant.CONSTANT.pInfo;


/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:48
 */
public class CanServer extends NioServer implements IServer {

    Integer port = 1080;


    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    public CanServer(Integer port) throws IOException {
        this.port = port;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);


        this.selector = Selector.open();
        SelectionKey selectionKey = this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new CanServerAcceptor(this.serverSocketChannel, this.selector));

    }


    @Override
    public void start() {
        try {
            pInfo("(LISTENING) SmartCanServer Listening on Port: " + port);
            this.run(this.selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // todo
    }

    @Override
    public void restart() {
        // todo
    }
}
