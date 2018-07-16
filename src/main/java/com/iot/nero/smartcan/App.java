package com.iot.nero.smartcan;

import com.iot.nero.smartcan.spi.MessagePush;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SmartCanBootstrap dfsBootstrap = new SmartCanBootstrap();
        dfsBootstrap.start();

        ServiceLoader<MessagePush> loaders = ServiceLoader.load(MessagePush.class);

        for (MessagePush in : loaders) {
            in.push("ss");
        }
    }
}