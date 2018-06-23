package com.iot.nero.smartcan.server;

import com.iot.nero.smartcan.constant.CONSTANT;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/5
 * Time   下午3:27
 */
public class CanServerAcceptor implements Runnable {
    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    public CanServerAcceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        if (this.serverSocketChannel != null) {
            try {
                SocketChannel socketChannel = this.serverSocketChannel.accept();
                CONSTANT.pInfo("(SocketChannel)" + socketChannel);
                new WorkerServeHandler(socketChannel, this.selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
