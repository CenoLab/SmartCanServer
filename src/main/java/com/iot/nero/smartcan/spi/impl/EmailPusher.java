package com.iot.nero.smartcan.spi.impl;

import com.iot.nero.smartcan.spi.MessagePush;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   1:30 PM
 */
public class EmailPusher implements MessagePush {
    @Override
    public void push(String message) {
        System.out.println("email push "+message);
    }
}
