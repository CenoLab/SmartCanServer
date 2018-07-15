package com.iot.nero.smartcan;

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