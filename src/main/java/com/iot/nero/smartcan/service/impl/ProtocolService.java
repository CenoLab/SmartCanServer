package com.iot.nero.smartcan.service.impl;

import com.iot.nero.smartcan.annotation.Service;
import com.iot.nero.smartcan.annotation.ServiceMethod;
import com.iot.nero.smartcan.config.ConfigLoader;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.entity.TokenPair;
import com.iot.nero.smartcan.entity.platoon.*;
import com.iot.nero.smartcan.factory.ConfigFactory;
import com.iot.nero.smartcan.service.IProtocolService;
import com.iot.nero.smartcan.utils.dbtools.DataBase;
import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.utils.dbtools.entity.Condition;
import com.iot.nero.smartcan.utils.dbtools.entity.Conditions;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.type.AsnType;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.iot.nero.smartcan.constant.CONSTANT.*;
import static com.iot.nero.smartcan.utils.ByteUtils.bytesToString;
import static com.iot.nero.smartcan.utils.ByteUtils.longToBytes;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/23
 * Time   10:02 AM
 */

@Service
public class ProtocolService implements IProtocolService {

    static Long syncNum = 0L;
    static Long msgCnt = 0L;
    int collectFrequency = ConfigFactory.getConfig().getCollectFrequency();
    int sendFrequency = ConfigFactory.getConfig().getSendFrequency();

    private Protocol protocol;

    private DataBase dataBase = new DataBase(
            ConfigFactory.getConfig().getDbDriver(),
            ConfigFactory.getConfig().getDbUrl(),
            ConfigFactory.getConfig().getDbUsername(),
            ConfigFactory.getConfig().getDbPwd());

    private Map<String,String> tokenMap = new HashMap<>();

    public ProtocolService() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
    }


    /**
     * 获取token
     * @param data
     * @return
     */
    private TokenPair getToken(Protocol data){
        List<String> selectColumns = new ArrayList<>();
        selectColumns.add("uniqueid");
        selectColumns.add("token");
        Conditions conditions = new Conditions();
        conditions.addCondition(new Condition("uniqueid","=","\'"+data.getInditicalCode()+"\'"));
        try {
            List<Map<String,Object>> result = dataBase.select(selectColumns,"car_info",conditions);
            if(!result.isEmpty()) {
                for (Map<String, Object> map : result) {
                    tokenMap.put(map.get("uniqueid").toString(), map.get("token").toString());
                    return new TokenPair(map.get("uniqueid").toString(), map.get("token").toString());
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新token
     * @param data
     * @param token
     * @return
     */
    private Integer updateToken(Protocol data,String... token){
        List<String> selectColumns = new ArrayList<>();
        selectColumns.add("token");

        Conditions conditions = new Conditions();
        conditions.addCondition(new Condition("uniqueid","=","\'"+bytesToString(data.getInditicalCode())+"\'"));
        try {
            Integer result = dataBase.update(selectColumns,"car_info",conditions,token);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建数据表
     * @param fields
     * @param table
     * @return
     */
    private synchronized int createTable(Field[] fields, String table) {
        List<String> tableColumns = new ArrayList<>();
        tableColumns.add("unique_id varchar(64) COLLATE utf8_general_ci");
        for (Field field : fields) {
            if (field.getType() != Integer.class &&
                    field.getType() != Long.class &&
                    field.getType() != Boolean.class &&
                    field.getType() != Double.class &&
                    field.getType() != byte.class &&
                    field.getType() != byte[].class &&
                    field.getType() != long.class &&
                    field.getType() != int.class &&
                    !field.getType().isEnum()
                    ) { // 不是基础类型

                if (field.getType() == AsnType.class) {

                } else if (field.getType() == Object[].class) {

                } else if (field.getType() == Object.class) {

                } else if (field.getType() == CompositeConverter.class) {

                } else if (field.getType() == Vector.class) {
                    Type types=field.getGenericType();
                    ParameterizedType pType= (ParameterizedType)types;//ParameterizedType是Type的子接口
                    String[] names = pType.getActualTypeArguments()[0].getTypeName().split("\\.");
                    try {
                        Class<?> clz = Class.forName(pType.getActualTypeArguments()[0].getTypeName());
                        createTable(clz.newInstance().getClass().getDeclaredFields(),names[names.length - 1]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    createTable(field.getType().getDeclaredFields(), field.getType().getSimpleName());
                }
            } else {
                String column = "";
                column = field.getName() + " varchar(64) COLLATE utf8_general_ci";
                tableColumns.add(column);
            }
        }
        tableColumns.add("create_time timestamp default current_timestamp");

        try {
            return dataBase.createTable(table, tableColumns);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 创建车队数据表
     * @param fields
     * @param columns
     * @param table
     * @return
     */
    private int createTeamTable(Field[] fields, List<String> columns, String table) {
        List<String> tableColumns = new ArrayList<>();
        tableColumns.add("unique_id varchar(64) COLLATE utf8_general_ci");
        if(!columns.isEmpty()){
            for(String column:columns){
                tableColumns.add(column+" varchar(64) COLLATE utf8_general_ci");
            }
        }
        for (Field field : fields) {
            if (field.getType() != Integer.class &&
                    field.getType() != Long.class &&
                    field.getType() != Boolean.class &&
                    field.getType() != Double.class &&
                    field.getType() != byte.class &&
                    field.getType() != byte[].class &&
                    field.getType() != long.class &&
                    field.getType() != int.class &&
                    !field.getType().isEnum()
                    ) { // 不是基础类型

                if (field.getType() == AsnType.class) {

                } else if (field.getType() == Object[].class) {

                } else if (field.getType() == Object.class) {

                } else if (field.getType() == CompositeConverter.class) {

                } else if (field.getType() == Vector.class) {
                    Type types=field.getGenericType();
                    ParameterizedType pType= (ParameterizedType)types;//ParameterizedType是Type的子接口
                    String[] names = pType.getActualTypeArguments()[0].getTypeName().split("\\.");
                    try {
                        Class<?> clz = Class.forName(pType.getActualTypeArguments()[0].getTypeName());
                        createTable(clz.newInstance().getClass().getDeclaredFields(),names[names.length - 1]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    createTable(field.getType().getDeclaredFields(), field.getType().getSimpleName());
                }
            } else {
                String column = "";
                column = field.getName() + " varchar(64) COLLATE utf8_general_ci";
                tableColumns.add(column);
            }
        }
        tableColumns.add("create_time timestamp default current_timestamp");

        try {
            return dataBase.createTable(table, tableColumns);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 写入数据到数据表
     * @param fields
     * @param table
     * @param object
     * @return
     */
    private synchronized int insertTable(Field[] fields, Class<?> table,Object object) {
        List<String> tableColumns = new ArrayList<>();
        List<Object> datas = new ArrayList<>();
        tableColumns.add("unique_id");
        datas.add(bytesToString(protocol.getInditicalCode()));
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getType() != Integer.class &&
                    field.getType() != Long.class &&
                    field.getType() != Boolean.class &&
                    field.getType() != Double.class &&
                    field.getType() != byte.class &&
                    field.getType() != byte[].class &&
                    field.getType() != long.class &&
                    field.getType() != int.class &&
                    !field.getType().isEnum()
                    ) { // 不是基础类型

                if (field.getType() == AsnType.class) {

                } else if (field.getType() == Object[].class) {

                } else if (field.getType() == Object.class) {

                } else if (field.getType() == CompositeConverter.class) {

                } else if (field.getType() == Vector.class) {
                    Type types=field.getGenericType();
                    ParameterizedType pType= (ParameterizedType)types;//ParameterizedType是Type的子接口

                    try {
                        Class<?> clz = Class.forName(pType.getActualTypeArguments()[0].getTypeName());
                        field.setAccessible(true);
                        Vector vector = (Vector)field.get(object);
                        for(int i = 0;i<pType.getActualTypeArguments().length;i++){
                            insertTable(clz.newInstance().getClass().getDeclaredFields(),clz,vector.get(i));
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setAccessible(true);
                    try {
                        insertTable(field.getType().getDeclaredFields(), field.getType(),field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                String column = "";
                column = field.getName();
                tableColumns.add(column);
                try {
                    if(field.getType()==byte[].class){
                        datas.add(bytesToString((byte[]) field.get(object)));
                    }else{
                        datas.add(String.valueOf(field.get(object)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        tableColumns.add("create_time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(System.currentTimeMillis()));   // 时间戳转换成时间
        datas.add(String.valueOf(sd));

        try {
            String[] names = table.getName().split("\\.");
            return dataBase.insert(names[names.length-1], tableColumns,datas);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 插入车队表
     * @param fields
     * @param table
     * @param object
     * @param values
     * @return
     */
    private int insertTeamTable(Field[] fields, Class<?> table, Object object, Map<String, String> values) {
        List<String> tableColumns = new ArrayList<>();
        List<Object> datas = new ArrayList<>();
        tableColumns.add("unique_id");
        datas.add(bytesToString(protocol.getInditicalCode()));
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getType() != Integer.class &&
                    field.getType() != Long.class &&
                    field.getType() != Boolean.class &&
                    field.getType() != Double.class &&
                    field.getType() != byte.class &&
                    field.getType() != byte[].class &&
                    field.getType() != long.class &&
                    field.getType() != int.class &&
                    !field.getType().isEnum()
                    ) { // 不是基础类型

                if (field.getType() == AsnType.class) {

                } else if (field.getType() == Object[].class) {

                } else if (field.getType() == Object.class) {

                } else if (field.getType() == CompositeConverter.class) {

                } else if (field.getType() == Vector.class) {
                    Type types=field.getGenericType();
                    ParameterizedType pType= (ParameterizedType)types;//ParameterizedType是Type的子接口

                    try {
                        Class<?> clz = Class.forName(pType.getActualTypeArguments()[0].getTypeName());
                        field.setAccessible(true);
                        Vector vector = (Vector)field.get(object);
                        for(int i = 0;i<pType.getActualTypeArguments().length;i++){
                            insertTable(clz.newInstance().getClass().getDeclaredFields(),clz,vector.get(i));
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    field.setAccessible(true);
                    try {
                        insertTable(field.getType().getDeclaredFields(), field.getType(),field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                String column = "";
                column = field.getName();
                tableColumns.add(column);
                try {
                    if(field.getType()==byte[].class){
                        datas.add(bytesToString((byte[]) field.get(object)));
                    }else{
                        datas.add(String.valueOf(field.get(object)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!values.isEmpty()){
            for(Map.Entry<String,String> entry:values.entrySet()){
                tableColumns.add(entry.getKey());
                datas.add(entry.getValue());
            }
        }

        tableColumns.add("create_time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(System.currentTimeMillis()));   // 时间戳转换成时间
        datas.add(String.valueOf(sd));

        try {
            String[] names = table.getName().split("\\.");
            return dataBase.insert(names[names.length-1], tableColumns,datas);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }


    /**
     * 日志记录
     * @param data
     * @param type
     * @param message
     */
    private void serverLog(Protocol data,String type,String message) {
        List<String> columns = new ArrayList<>();
        columns.add("unique_id");
        columns.add("type");
        columns.add("message");
        List<Object> objs = new ArrayList<>();
        objs.add(bytesToString(data.getInditicalCode()));
        objs.add(type);
        objs.add(message);
        try {
            dataBase.insert(ConfigFactory.getConfig().getLogTableName(),columns,objs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * socket 写回
     * @param command
     * @param outputStream
     * @param socketChannel
     * @throws IOException
     */
    private void writeToSocket(byte command, ByteArrayOutputStream outputStream, final SocketChannel socketChannel) throws IOException {

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
        this.protocol = data;

        InputStream inputStream = new ByteArrayInputStream(data.dataUnit);
        LoginRequestMessage loginRequestMessage = LoginRequestMessage.ber_decode(inputStream);

        String token = UUID.randomUUID().toString();

        TokenPair tokenPair = getToken(data);
        if(tokenPair==null){
            serverLog(data,LOG_TYPE_WARNING,LOG_MESSAGE_UNKNOWN_LOGIN_CAR);
            LoginResponseMessage loginResponseMessage = new LoginResponseMessage();
            loginResponseMessage.syncNum = loginRequestMessage.syncNum + 1;
            loginResponseMessage.token = "NULL".getBytes();
            loginResponseMessage.msgCnt = loginRequestMessage.msgCnt + 1;
            loginResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
            loginResponseMessage.vid = loginRequestMessage.iccid;
            loginResponseMessage.loginResult = true;
            loginResponseMessage.errorCode = new byte[]{0x01};
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
        }else{
            // 此处更新数据库token
            updateToken(data,token);
            serverLog(data,LOG_TYPE_INFO,LOG_MESSAGE_LOGIN_CAR);
            LoginResponseMessage loginResponseMessage = new LoginResponseMessage();
            loginResponseMessage.syncNum = loginRequestMessage.syncNum + 1;
            loginResponseMessage.token = token.getBytes();
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
        }


        // 储存数据
        Field[] fields = LoginRequestMessage.class.getDeclaredFields();
        String[] names = loginRequestMessage.getClass().getName().split("\\.");

        createTable(fields, names[names.length - 1]);
        insertTable(fields,LoginRequestMessage.class,loginRequestMessage);
    }



    @Override
    @ServiceMethod((byte) 0x04)
    public void logout(Protocol data, final SocketChannel socketChannel) throws IOException {
        this.protocol = data;

        InputStream inputStream = new ByteArrayInputStream(data.dataUnit);
        LogoutRequestMessage logoutRequestMessage = LogoutRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(data.getInditicalCode()));
        if(token==null || token.equals(bytesToString(logoutRequestMessage.token))){
            serverLog(data,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        LogoutResponseMessage logoutResponseMessage = new LogoutResponseMessage();
        logoutResponseMessage.syncNum = logoutRequestMessage.syncNum + 1;
        logoutResponseMessage.logoutResult = true;
        logoutResponseMessage.timestamp = longToBytes(System.currentTimeMillis());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        logoutResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xF4, outputStream, socketChannel);

        // 储存数据
        Field[] fields = LogoutRequestMessage.class.getDeclaredFields();
        String[] names = logoutRequestMessage.getClass().getName().split("\\.");

        createTable(fields, names[names.length - 1]);
        insertTable(fields,LogoutRequestMessage.class,logoutRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xC1)
    public void heartBeat(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SyncRequestMessage syncRequestMessage = SyncRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(syncRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SyncResponseMessage syncResponseMessage = new SyncResponseMessage();
        syncResponseMessage.syncNum = syncRequestMessage.syncNum + 1;
        syncResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        syncResponseMessage.syncResult = true;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        syncResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE1, outputStream, socketChannel);
        // 储存数据
        Field[] fields = SyncRequestMessage.class.getDeclaredFields();
        String[] names = syncRequestMessage.getClass().getName().split("\\.");

        createTable(fields, names[names.length - 1]);
        insertTable(fields,SyncRequestMessage.class,syncRequestMessage);

    }


    @Override
    @ServiceMethod((byte) 0xC6)
    public void smartCan(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartCanRequestBody smartCarRequestBody = SmartCanRequestBody.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartCarRequestBody.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartCanResponseMessage smartCarResponseMessage = new SmartCanResponseMessage();
        smartCarResponseMessage.syncNum = smartCarRequestBody.msgCnt + 1;
        smartCarResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartCarResponseMessage.errorCode = new byte[]{0x01};
        smartCarResponseMessage.msgcnt = smartCarRequestBody.msgCnt + 1;
        smartCarResponseMessage.msgStatus = true;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartCarResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE6, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartCanRequestBody.class.getDeclaredFields();
        String[] names = smartCarRequestBody.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartCanRequestBody.class,smartCarRequestBody);
    }

    @Override
    @ServiceMethod((byte) 0xC7)
    public void stmartRecogrize(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartRecognizeRequestMessage smartRecognizeRequestMessage = SmartRecognizeRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartRecognizeRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }


        SmartRecognizeResponseMessage smartRecognizeResponseMessage = new SmartRecognizeResponseMessage();
        smartRecognizeResponseMessage.syncNum = smartRecognizeRequestMessage.syncNum + 1;
        smartRecognizeResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartRecognizeResponseMessage.errorCode = new byte[]{0x00};
        smartRecognizeResponseMessage.msgStatus = true;
        smartRecognizeResponseMessage.msgCnt = smartRecognizeRequestMessage.msgCnt + 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartRecognizeResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE7, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartRecognizeRequestMessage.class.getDeclaredFields();
        String[] names = smartRecognizeRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartRecognizeRequestMessage.class,smartRecognizeRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xC8)
    public void smartStrategy(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartStrategyRequestMessage smartStrategyRequestMessage = SmartStrategyRequestMessage.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartStrategyRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartStrategyResponseMessage smartStrategyResponseMessage = new SmartStrategyResponseMessage();
        smartStrategyResponseMessage.syncNum = smartStrategyRequestMessage.syncNum + 1;
        smartStrategyResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartStrategyResponseMessage.errorCode = new byte[]{0x00};
        smartStrategyResponseMessage.msgStatus = true;
        smartStrategyResponseMessage.msgCnt = smartStrategyRequestMessage.msgCnt + 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartStrategyResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE8, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartStrategyRequestMessage.class.getDeclaredFields();
        String[] names = smartStrategyRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartStrategyRequestMessage.class,smartStrategyRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xC9)
    public void smartControl(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartControlRequestMessage smartControlRequestMessage = SmartControlRequestMessage.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartControlRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartControlResponseMessage smartControlResponseMessage = new SmartControlResponseMessage();
        smartControlResponseMessage.syncNum = smartControlRequestMessage.syncNum + 1;
        smartControlResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartControlResponseMessage.errorCode = new byte[]{0x00};
        smartControlResponseMessage.msgStatus = true;
        smartControlResponseMessage.msgCnt = smartControlRequestMessage.msgCnt + 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartControlResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xE9, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartControlRequestMessage.class.getDeclaredFields();
        String[] names = smartControlRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartControlRequestMessage.class,smartControlRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xCA)
    public void smartControlFeed(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;

        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartCtrlFeedBackRequestMessage smartCtrlFeedBackRequestMessage = SmartCtrlFeedBackRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartCtrlFeedBackRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }


        SmartCtrlFeedBackResponseMessage smartCtrlFeedBackResponseMessage = new SmartCtrlFeedBackResponseMessage();
        smartCtrlFeedBackResponseMessage.syncNum = smartCtrlFeedBackRequestMessage.syncNum + 1;
        smartCtrlFeedBackResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartCtrlFeedBackResponseMessage.errorCode = new byte[]{0x00};
        smartCtrlFeedBackResponseMessage.msgStatus = true;
        smartCtrlFeedBackResponseMessage.msgCnt = smartCtrlFeedBackRequestMessage.msgCnt + 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartCtrlFeedBackResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xEA, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartCtrlFeedBackRequestMessage.class.getDeclaredFields();
        String[] names = smartCtrlFeedBackRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartCtrlFeedBackRequestMessage.class,smartCtrlFeedBackRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xCB)
    public void smartFault(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartFaultRequestMessage smartFaultRequestMessage = SmartFaultRequestMessage.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartFaultRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }


        SmartFaultResponseMessage smartFaultResponseMessage = new SmartFaultResponseMessage();
        smartFaultResponseMessage.syncNum = smartFaultRequestMessage.syncNum + 1;
        smartFaultResponseMessage.msgCnt = smartFaultRequestMessage.msgCnt + 1;
        smartFaultResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartFaultResponseMessage.errorCode = new byte[]{0x00};
        smartFaultResponseMessage.msgStatus = true;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartFaultResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xEB, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartFaultRequestMessage.class.getDeclaredFields();
        String[] names = smartFaultRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartFaultRequestMessage.class,smartFaultRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xCC)
    public void smartFormATeam(Protocol protocol, final SocketChannel socketChannel) throws IOException {
       this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartFromATeamRequestMessage smartFromATeamRequestMessage = SmartFromATeamRequestMessage.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartFromATeamRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        // 车队id生成
        String teamId = UUID.randomUUID().toString();

        SmartFromATeamResponseMessage smartFromATeamResponseMessage = new SmartFromATeamResponseMessage();
        smartFromATeamResponseMessage.syncNum = smartFromATeamRequestMessage.syncNum + 1;
        smartFromATeamResponseMessage.msgCount = smartFromATeamRequestMessage.msgCount + 1;
        smartFromATeamResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartFromATeamResponseMessage.errorCode = new byte[]{0x00};
        smartFromATeamResponseMessage.msgStatus = true;
        smartFromATeamResponseMessage.msgid = new byte[]{0x00};

        //车队id赋值
        smartFromATeamResponseMessage.id = teamId.getBytes();


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartFromATeamResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xEC, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartFromATeamRequestMessage.class.getDeclaredFields();
        String[] names = smartFromATeamRequestMessage.getClass().getName().split("\\.");
        List<String> columns = new ArrayList<>();
        columns.add("id");

        createTeamTable(fields,columns, names[names.length - 1]);

        Map<String,String> values = new HashMap<>();
        values.put("id",teamId);

        insertTeamTable(fields,SmartFromATeamRequestMessage.class,smartFromATeamRequestMessage,values);
    }


    @Override
    @ServiceMethod((byte) 0xCD)
    public void smartFTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartFTeamSuccessRequestMessage smartFTeamSuccessRequestMessage = SmartFTeamSuccessRequestMessage.ber_decode(inputStream);


        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartFTeamSuccessRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartFTeamSuccessResponseMessage smartFTeamSuccessResponseMessage = new SmartFTeamSuccessResponseMessage();
        smartFTeamSuccessResponseMessage.syncNum = smartFTeamSuccessRequestMessage.syncNum + 1;
        smartFTeamSuccessResponseMessage.msgCount = smartFTeamSuccessRequestMessage.msgCount + 1;
        smartFTeamSuccessResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartFTeamSuccessResponseMessage.errorCode = new byte[]{0x00};
        smartFTeamSuccessResponseMessage.msgStatus = true;
        smartFTeamSuccessResponseMessage.msgid = new byte[]{0x00};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartFTeamSuccessResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xED, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartFTeamSuccessRequestMessage.class.getDeclaredFields();
        String[] names = smartFTeamSuccessRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartFTeamSuccessRequestMessage.class,smartFTeamSuccessRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xCE)
    public void smartDissolveTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartDissolveRequestMessage smartDissolveRequestMessage = SmartDissolveRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartDissolveRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartDissolveResponseMessage smartDissolveResponseMessage = new SmartDissolveResponseMessage();
        smartDissolveResponseMessage.syncNum = smartDissolveRequestMessage.syncNum + 1;
        smartDissolveResponseMessage.msgCount = smartDissolveRequestMessage.msgCount + 1;
        smartDissolveResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartDissolveResponseMessage.errorCode = new byte[]{0x00};
        smartDissolveResponseMessage.msgStatus = true;
        smartDissolveResponseMessage.msgid = new byte[]{0x00};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartDissolveResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xEE, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartDissolveRequestMessage.class.getDeclaredFields();
        String[] names = smartDissolveRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartDissolveRequestMessage.class,smartDissolveRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xD0)
    public void smartTeam(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartTeamRequestMessage smartTeamRequestMessage = SmartTeamRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartTeamRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartTeamResponseMessage smartTeamResponseMessage = new SmartTeamResponseMessage();
        smartTeamResponseMessage.syncNum = smartTeamRequestMessage.syncNum + 1;
        smartTeamResponseMessage.msgCount = smartTeamRequestMessage.msgCount + 1;
        smartTeamResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartTeamResponseMessage.errorCode = new byte[]{0x00};
        smartTeamResponseMessage.msgStatus = true;
        smartTeamResponseMessage.msgid = new byte[]{0x00};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartTeamResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xF5, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartTeamRequestMessage.class.getDeclaredFields();
        String[] names = smartTeamRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartTeamRequestMessage.class,smartTeamRequestMessage);
    }

    @Override
    @ServiceMethod((byte) 0xCF)
    public void smartPlatonning(Protocol protocol, final SocketChannel socketChannel) throws IOException {
        this.protocol = protocol;
        InputStream inputStream = new ByteArrayInputStream(protocol.dataUnit);
        SmartPlatonningRequestMessage smartPlatonningRequestMessage = SmartPlatonningRequestMessage.ber_decode(inputStream);

        String token = tokenMap.get(bytesToString(protocol.getInditicalCode()));
        if(token==null || token.equals(bytesToString(smartPlatonningRequestMessage.token))){
            serverLog(protocol,LOG_TYPE_WARNING,LOG_MESSAGE_TOKEN_LOGIN_INCORRECT);
        }

        SmartPlatonningResponseMessage smartPlatonningResponseMessage = new SmartPlatonningResponseMessage();
        smartPlatonningResponseMessage.syncNum = smartPlatonningRequestMessage.syncNum + 1;
        smartPlatonningResponseMessage.msgCount = smartPlatonningRequestMessage.msgCount + 1;
        smartPlatonningResponseMessage.timestamp = longToBytes(System.currentTimeMillis());
        smartPlatonningResponseMessage.errorCode = new byte[]{0x00};
        smartPlatonningResponseMessage.msgStatus = true;
        smartPlatonningResponseMessage.msgid = new byte[]{0x00};


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smartPlatonningResponseMessage.ber_encode(outputStream);

        // 返回响应
        writeToSocket((byte) 0xEF, outputStream, socketChannel);

        // 储存数据
        Field[] fields = SmartPlatonningRequestMessage.class.getDeclaredFields();
        String[] names = smartPlatonningRequestMessage.getClass().getName().split("\\.");
        createTable(fields, names[names.length - 1]);
        insertTable(fields,SmartPlatonningRequestMessage.class,smartPlatonningRequestMessage);
    }

}

// 创建中 创建成功 已解散
