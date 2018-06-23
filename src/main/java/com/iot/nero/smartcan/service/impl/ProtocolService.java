package com.iot.nero.smartcan.service.impl;

import com.iot.nero.smartcan.annotation.Service;
import com.iot.nero.smartcan.annotation.ServiceMethod;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.entity.car.*;
import com.iot.nero.smartcan.service.IProtocolService;
import com.iot.nero.smartcan.utils.dbtools.DataBase;
import com.iot.nero.smartcan.constant.CONSTANT;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/23
 * Time   10:02 AM
 */
@Service
public class ProtocolService implements IProtocolService {


    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/autobrain_data";
    private String username = "root";
    private String pd = "baby..520587";

    static Long syncNum = 0L;
    static Long msgCnt = 0L;
    byte[] token = new byte[]{(byte) 0xcc, (byte) 0xff, (byte) 0xed, (byte) 0xcf};
    int collectFrequency = 1000;
    int sendFrequency = 1000;

    private DataBase dataBase = new DataBase(driver, url, username, pd);


    private int createTable(Field[] fields, String table) {
        List<String> tableColumns = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() != Integer.class &&
                    field.getType() != Long.class &&
                    field.getType() != Boolean.class &&
                    field.getType() != byte.class &&
                    field.getType() != Double.class
                    ) { // 不是基础类型
                String[] names = field.getClass().getName().split("\\.");
                createTable(field.getClass().getDeclaredFields(),names[names.length-1]);
            } else {
                String column = "";
                column = field.getName() + " varchar(512)";
                tableColumns.add(column);
            }
        }


        try {
            return dataBase.createTable(table, tableColumns);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private byte[] longToBytes(long v) {

        byte[] writeBuffer = new byte[8];

        writeBuffer[0] = (byte) (v >>> 56);
        writeBuffer[1] = (byte) (v >>> 48);
        writeBuffer[2] = (byte) (v >>> 40);
        writeBuffer[3] = (byte) (v >>> 32);
        writeBuffer[4] = (byte) (v >>> 24);
        writeBuffer[5] = (byte) (v >>> 16);
        writeBuffer[6] = (byte) (v >>> 8);
        writeBuffer[7] = (byte) (v >>> 0);

        return writeBuffer;
    }

    private void writeToSocket(byte command, ByteArrayOutputStream outputStream, SocketChannel socketChannel) throws IOException {

        byte[] out = outputStream.toByteArray();
        outputStream.write(out);

        Protocol res = new Protocol();
        res.commandUnit[1] = command;
        res.dataUnit = out;
        res.dataUnitLength = (short) out.length;
        res.dataUnitEncryptMethod = 0x11;
        res.inditicalCode = new byte[17];
        res.startSymbol = new byte[]{0x23, 0x23};

        socketChannel.write(ByteBuffer.wrap(res.toByte()));
        CONSTANT.pInfo("(WRITE)" + outputStream.toString());
    }

    @Override
    @ServiceMethod((byte) 0x01)
    public void login(Protocol data, final SocketChannel socketChannel) throws IOException {

        InputStream inputStream = new ByteArrayInputStream(data.dataUnit);
        LoginRequestMessage loginRequestMessage = LoginRequestMessage.ber_decode(inputStream);


        LoginResponseMessage loginResponseMessage = new LoginResponseMessage();
        loginResponseMessage.syncNum = loginRequestMessage.syncNum + 1;
        loginResponseMessage.token = token;
        loginResponseMessage.msgCnt = loginRequestMessage.msgCnt + 1;
        loginResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        loginResponseMessage.vid = loginRequestMessage.iccid;
        loginResponseMessage.loginResult = true;
        loginResponseMessage.errorCode = new byte[]{0x00};
        Vector<CollectConfigMessage> collectConfs = new Vector<>();
        CollectConfigMessage collectConfigMessage = new CollectConfigMessage();

        collectConfigMessage.msgid = new byte[]{0x01};
        collectConfigMessage.collectFrequency = collectFrequency;
        collectConfigMessage.sendFrequency = sendFrequency;

        collectConfs.add(collectConfigMessage);
        loginResponseMessage.collectConfs = collectConfs;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loginResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xF1, outputStream, socketChannel);

        // 储存数据
        Field[] fields = LoginRequestMessage.class.getDeclaredFields();
        String[] names = loginRequestMessage.getClass().getName().split("\\.");

        createTable(fields, names[names.length - 1]);
    }

    @Override
    @ServiceMethod((byte) 0x04)
    public void logout(Protocol logoutRequestMessage, final SocketChannel socketChannel) {

    }

    @Override
    @ServiceMethod((byte) 0xC1)
    public void heartBeat(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SyncRequestMessage syncRequestMessage = SyncRequestMessage.ber_decode(inputStream);


        SyncResponseMessage syncResponseMessage = new SyncResponseMessage();
        syncResponseMessage.syncNum = syncRequestMessage.syncNum + 1;
        syncResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        syncResponseMessage.syncResult = true;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        syncResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE1, outputStream, socketChannel);
    }


    @Override
    @ServiceMethod((byte) 0xC6)
    public void smartCan(Protocol protocol, SocketChannel socketChannel) throws IOException {
        //
    }

    @Override
    @ServiceMethod((byte) 0xC7)
    public void stmartRecogrize(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xC8)
    public void smartStrategy(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xC9)
    public void smartControl(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCA)
    public void smartControlFeed(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCB)
    public void smartFault(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCC)
    public void smartFormATeam(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCD)
    public void smartFTeam(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCE)
    public void smartDissolveTeam(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xD0)
    public void smartTeam(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }

    @Override
    @ServiceMethod((byte) 0xCF)
    public void smartPlatonning(Protocol protocol, SocketChannel socketChannel) throws IOException {

    }


}
