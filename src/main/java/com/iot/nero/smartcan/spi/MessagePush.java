package com.iot.nero.smartcan.spi;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   1:27 PM
 */
public interface MessagePush {

    /**
     * 消息发送
     * @param message
     */
    void push(String message);

}
