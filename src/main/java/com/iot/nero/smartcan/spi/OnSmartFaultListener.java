package com.iot.nero.smartcan.spi;

import com.iot.nero.smartcan.entity.platoon.SmartFaultRequestMessage;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:25 PM
 */
public interface OnSmartFaultListener {

    /**
     * 异常
     * @param smartFaultRequestMessage
     */
    void onFault(SmartFaultRequestMessage smartFaultRequestMessage);
}
