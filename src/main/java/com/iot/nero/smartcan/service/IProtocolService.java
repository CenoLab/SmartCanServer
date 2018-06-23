package com.iot.nero.smartcan.service;

import com.iot.nero.smartcan.core.Protocol;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/23
 * Time   10:02 AM
 */
public interface IProtocolService {

    void login(Protocol protocol, final SocketChannel socketChannel) throws IOException;

    void logout(Protocol protocol,final SocketChannel socketChannel);

    void heartBeat(Protocol protocol,final SocketChannel socketChannel) throws IOException;

    void smartCan(Protocol protocol,final SocketChannel socketChannel) throws IOException;

    void stmartRecogrize(Protocol protocol,final SocketChannel socketChannel) throws IOException;

    void smartStrategy(Protocol protocol,final SocketChannel socketChannel) throws IOException;

    void smartControl(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartControlFeed(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartFault(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartFormATeam(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartFTeam(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartDissolveTeam(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartTeam(Protocol protocol,final SocketChannel socketChannel) throws IOException;


    void smartPlatonning(Protocol protocol,final SocketChannel socketChannel) throws IOException;


}
