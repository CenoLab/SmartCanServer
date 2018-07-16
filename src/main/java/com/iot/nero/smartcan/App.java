package com.iot.nero.smartcan;

import com.iot.nero.smartcan.spi.impl.SmartFaultListener;

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
    }
}