package com.iot.nero.smartcan.spi.impl;

import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.spi.OnMessageReceivedListener;

import static com.iot.nero.smartcan.constant.CONSTANT.pInfo;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:28 PM
 */
public class MessageReceivedListener implements OnMessageReceivedListener {
    @Override
    public void OnMessageReceived(Protocol protocol) {
        pInfo("(SPI) message received");
    }
}
