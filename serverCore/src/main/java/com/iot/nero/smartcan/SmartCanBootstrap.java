package com.iot.nero.smartcan;

import com.iot.nero.smartcan.annotation.Service;
import com.iot.nero.smartcan.annotation.ServiceMethod;
import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.factory.ConfigFactory;
import com.iot.nero.smartcan.server.CanServer;
import com.iot.nero.smartcan.server.IServer;
import com.iot.nero.smartcan.spi.OnMessageReceivedListener;
import com.iot.nero.smartcan.utils.ClassUtil;
import com.iot.nero.smartcan.utils.JarUtils;
import com.iot.nero.smartcan.utils.dbtools.DataBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import static com.iot.nero.smartcan.constant.CONSTANT.*;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午2:33
 */
public class SmartCanBootstrap {

    private Integer DFS_SERVER_LISTEN_PORT = 1080;

    public static Map<Byte,Method> autoBrainServiceMap;

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


    public void runListener() throws IOException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        initTable();

        initPlugin();

        initService();
        DFS_SERVER_LISTEN_PORT = ConfigFactory.getConfig().getPort();
        IServer ndfsServer = new CanServer(DFS_SERVER_LISTEN_PORT);
        ndfsServer.start();
    }

    /**
     *  初始化插件
     */
    private void initPlugin() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        String pluginPath = System.getProperty("user.dir") + "/"+ConfigFactory.getConfig().getPluginPath();
        pInfo("(PLUGIN) "+pluginPath);
        // 扫描插件
        File file = new File(pluginPath);
        if(file.isDirectory()){
            File [] files = file.listFiles();
            for(File plugin:files){
                if(plugin.isFile() && plugin.getName().endsWith("jar")){
                    pInfo("(PLUGIN) "+FIND_PLUGIN+plugin.getName());
                    Object messageReceivedListener = JarUtils.loadJar(plugin.getAbsolutePath(), "com.iot.nero.smartcan.plugin.impl.MessageReceivedListener");
                    pInfo("(EXTENSION) "+messageReceivedListener);
                    Object smartFaultListener = JarUtils.loadJar(plugin.getAbsolutePath(), "com.iot.nero.smartcan.plugin.impl.SmartFaultListener");
                    pInfo("(EXTENSION) "+smartFaultListener);
                }
            }
        }else {
            pInfo("（PLUGIN）"+PLUGIN_PATH_ERROR);
        }
    }

    private void initTable() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

        final DataBase dataBase = new DataBase(
                ConfigFactory.getConfig().getDbDriver(),
                ConfigFactory.getConfig().getDbUrl(),
                ConfigFactory.getConfig().getDbUsername(),
                ConfigFactory.getConfig().getDbPwd());

        List<String> columns = new ArrayList<>();
        columns.add("unique_id varchar(64) COLLATE utf8_general_ci");
        columns.add("type varchar(64) COLLATE utf8_general_ci");
        columns.add("message varchar(64) COLLATE utf8_general_ci");
        columns.add("create_time timestamp default current_timestamp");

        try {
            dataBase.createTable(ConfigFactory.getConfig().getLogTableName(),columns);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    dataBase.tick();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 200000 , ConfigFactory.getConfig().getDbTickInterval());

    }

    public void start() {
        CONSTANT.printNdfsInfo();
        try {
            runListener();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
