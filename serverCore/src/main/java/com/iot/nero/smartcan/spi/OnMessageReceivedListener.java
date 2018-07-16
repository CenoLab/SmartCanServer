package com.iot.nero.smartcan.spi;

import com.iot.nero.smartcan.core.Protocol;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:26 PM
 */
public interface OnMessageReceivedListener {
    /**
     * 数据收到
     * @param protocol
     */
    void OnMessageReceived(Protocol protocol);
}
