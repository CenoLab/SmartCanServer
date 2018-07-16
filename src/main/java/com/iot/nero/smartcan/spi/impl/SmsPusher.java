package com.iot.nero.smartcan.spi.impl;

import com.iot.nero.smartcan.spi.MessagePush;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   1:29 PM
 */
public class SmsPusher implements MessagePush {
    @Override
    public void push(String message) {
        System.out.println("sms push "+message);
    }
}
