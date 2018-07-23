package com.iot.nero.smartcan.spi;

import com.iot.nero.smartcan.entity.platoon.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SocketChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/17
 * Time   9:04 PM
 */
public interface OnMessageDecodeListener {
    /**
     * 登录
     * @param loginRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onLogin(LoginRequestMessage loginRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 登出
     * @param logoutRequestMessage
     * @param socketChannel
     */
    void onLogout(LogoutRequestMessage logoutRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 心跳
     * @param syncRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onHeartBeat(SyncRequestMessage syncRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 车身数据集
     * @param smartCanRequestBody
     * @param socketChannel
     * @throws IOException
     */
    void onSmartCan(SmartCanRequestBody smartCanRequestBody, final SocketChannel socketChannel) throws IOException;

    /**
     * 感知数据集
     * @param smartRecognizeRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartRecogrize(SmartRecognizeRequestMessage smartRecognizeRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 决策数据集
     * @param smartStrategyRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartStrategy(SmartStrategyRequestMessage smartStrategyRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 控制数据集
     * @param smartControlRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartControl(SmartControlRequestMessage smartControlRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 控制反馈数据集
     * @param smartCtrlFeedBackRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartControlFeed(SmartCtrlFeedBackRequestMessage smartCtrlFeedBackRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 错误
     * @param smartFaultRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartFault(SmartFaultRequestMessage smartFaultRequestMessage, final SocketChannel socketChannel) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 创建车队
     * @param smartFromATeamRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartFormATeam(SmartFromATeamRequestMessage smartFromATeamRequestMessage, final SocketChannel socketChannel) throws IOException;


    /**
     * 车队创建成功
     * @param smartFTeamSuccessRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartFTeam(SmartFTeamSuccessRequestMessage smartFTeamSuccessRequestMessage, final SocketChannel socketChannel) throws IOException;


    /**
     * 车队解散
     * @param smartDissolveRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartDissolveTeam(SmartDissolveRequestMessage smartDissolveRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 车队数据集
     * @param smartTeamRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartTeam(SmartTeamRequestMessage smartTeamRequestMessage, final SocketChannel socketChannel) throws IOException;

    /**
     * 车辆在车队中状态
     * @param smartPlatonningRequestMessage
     * @param socketChannel
     * @throws IOException
     */
    void onSmartPlatonning(SmartPlatonningRequestMessage smartPlatonningRequestMessage, final SocketChannel socketChannel) throws IOException;

}
