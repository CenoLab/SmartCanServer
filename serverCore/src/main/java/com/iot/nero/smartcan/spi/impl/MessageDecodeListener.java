package com.iot.nero.smartcan.spi.impl;

import com.iot.nero.smartcan.entity.platoon.*;
import com.iot.nero.smartcan.spi.OnMessageDecodeListener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SocketChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/17
 * Time   9:06 PM
 */
public class MessageDecodeListener implements OnMessageDecodeListener {

    @Override
    public void onLogin(LoginRequestMessage loginRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onLogout(LogoutRequestMessage logoutRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onHeartBeat(SyncRequestMessage syncRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartCan(SmartCanRequestBody smartCanRequestBody, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartRecogrize(SmartRecognizeRequestMessage smartRecognizeRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartStrategy(SmartStrategyRequestMessage smartStrategyRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartControl(SmartControlRequestMessage smartControlRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartControlFeed(SmartCtrlFeedBackRequestMessage smartCtrlFeedBackRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartFault(SmartFaultRequestMessage smartFaultRequestMessage, SocketChannel socketChannel) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    }

    @Override
    public void onSmartFormATeam(SmartFromATeamRequestMessage smartFromATeamRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartFTeam(SmartFTeamSuccessRequestMessage smartFTeamSuccessRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartDissolveTeam(SmartDissolveRequestMessage smartDissolveRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartTeam(SmartTeamRequestMessage smartTeamRequestMessage, SocketChannel socketChannel) throws IOException {

    }

    @Override
    public void onSmartPlatonning(SmartPlatonningRequestMessage smartPlatonningRequestMessage, SocketChannel socketChannel) throws IOException {

    }
}
