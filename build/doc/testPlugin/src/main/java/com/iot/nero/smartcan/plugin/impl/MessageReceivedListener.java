package com.iot.nero.smartcan.plugin.impl;

import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.spi.OnMessageReceivedListener;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:28 PM
 */
public class MessageReceivedListener implements OnMessageReceivedListener {
    @Override
    public void OnMessageReceived(Protocol protocol) {
        System.out.println("this from plugin2 message received");
    }
}
