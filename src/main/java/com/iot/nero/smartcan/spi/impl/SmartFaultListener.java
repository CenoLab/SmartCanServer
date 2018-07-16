package com.iot.nero.smartcan.spi.impl;

import com.iot.nero.smartcan.entity.platoon.SmartFaultRequestMessage;
import com.iot.nero.smartcan.spi.OnSmartFaultListener;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:28 PM
 */
public class SmartFaultListener implements OnSmartFaultListener {
    @Override
    public void onFault(SmartFaultRequestMessage smartFaultRequestMessage) {
        System.out.println("smart fault");
    }
}
