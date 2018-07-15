package com.iot.nero.smartcan;

import com.iot.nero.smartcan.annotation.Service;
import com.iot.nero.smartcan.annotation.ServiceMethod;
import com.iot.nero.smartcan.config.Config;
import com.iot.nero.smartcan.config.ConfigLoader;
import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.server.CanServer;
import com.iot.nero.smartcan.server.IServer;
import com.iot.nero.smartcan.utils.ClassUtil;
import com.iot.nero.smartcan.utils.dbtools.DataBase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.iot.nero.smartcan.constant.CONSTANT.*;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午2:33
 */
public class SmartCanBootstrap {

    private Integer DFS_SERVER_LISTEN_PORT = 1080;

    private Config config;

    public static Map<Byte,Method> autoBrainServiceMap;


    public void loadConfig() {
        ConfigLoader configLoader = new ConfigLoader();
        try {
            config = configLoader.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initService(){
        autoBrainServiceMap = new HashMap<>();// 服务容器
        List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(App.class.getPackage()); // 服务扫描

        for(Class<?> s:clsList){
            Service at = s.getAnnotation(Service.class);
            if (at!= null) {
                Method[] methods = s.getMethods();
                for(Method m:methods){
                    ServiceMethod autoBrainService = m.getAnnotation(ServiceMethod.class);
                    if(autoBrainService!=null){
                        CONSTANT.pInfo("(Service) ["+s.getName()+"]->["+m.getName()+"]");
                        autoBrainServiceMap.put(autoBrainService.value(),m);// 服务注册
                    }
                }
            }
        }
    }


    public void runFileListener() throws IOException {
        initTable();
        initService();
        IServer ndfsServer = new CanServer(DFS_SERVER_LISTEN_PORT);
        ndfsServer.start();
    }

    private void initTable() {
        DataBase dataBase = new DataBase(DB_DRIVER, DB_URL, DB_USERNAME, DB_PD);
        List<String> columns = new ArrayList<>();
        columns.add("unique_id varchar(64) COLLATE utf8_general_ci");
        columns.add("type varchar(64) COLLATE utf8_general_ci");
        columns.add("message varchar(64) COLLATE utf8_general_ci");
        columns.add("create_time timestamp default current_timestamp");
        try {
            dataBase.createTable(LOG_TABLE_NAME,columns);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        CONSTANT.printNdfsInfo();
        loadConfig();
        try {
            runFileListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
