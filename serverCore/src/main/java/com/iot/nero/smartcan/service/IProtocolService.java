package com.iot.nero.smartcan.service;

import com.iot.nero.smartcan.core.Protocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SocketChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/23
 * Time   10:02 AM
 */
public interface IProtocolService {

    /**
     * 登录
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void login(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 登出
     * @param protocol
     * @param socketChannel
     */
    void logout(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 心跳
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void heartBeat(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 车身数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartCan(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 感知数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void stmartRecogrize(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 决策数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartStrategy(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 控制数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartControl(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 控制反馈数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartControlFeed(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 错误
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartFault(Protocol protocol, final SocketChannel socketChannel) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 创建车队
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartFormATeam(Protocol protocol, final SocketChannel socketChannel) throws IOException;


    /**
     * 车队创建成功
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartFTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException;


    /**
     * 车队解散
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartDissolveTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 车队数据集
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    /**
     * 车辆在车队中状态
     * @param protocol
     * @param socketChannel
     * @throws IOException
     */
    void smartPlatonning(Protocol protocol, final SocketChannel socketChannel) throws IOException;


}
