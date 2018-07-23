package com.iot.nero.smartcan;

import com.iot.nero.smartcan.annotation.Service;
import com.iot.nero.smartcan.annotation.ServiceMethod;
import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.factory.ConfigFactory;
import com.iot.nero.smartcan.license.core.LicenseVertify;
import com.iot.nero.smartcan.server.CanServer;
import com.iot.nero.smartcan.server.IServer;
import com.iot.nero.smartcan.utils.classandjar.ClassUtil;
import com.iot.nero.smartcan.utils.classandjar.JarUtils;
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

    private Integer CAN_SERVER_LISTEN_PORT = 1080;


    public static Map<Byte,Method> autoBrainServiceMap;

    private void initService(){
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

    // license checker
    private void initLicense() {
        LicenseVertify vlicense=new LicenseVertify("license"); // 项目唯一识别码，对应生成配置文件的subject
        vlicense.install(System.getProperty("user.dir")+"/build/license/");
        switch (vlicense.vertify()){
            case 0:
                pInfo("(LICENSE) CHECKED!");
                break;
            case 1:
                pInfo("(LICENSE) EXPERIED!");
                break;
            case 2:
                pInfo("(LICENSE) FAILED!");
                break;
        }
    }


    private void runListener() throws IOException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        initLicense();

        initTable();

        initPlugin();

        initService();
        CAN_SERVER_LISTEN_PORT = ConfigFactory.getConfig().getPort();
        IServer canServer = new CanServer(CAN_SERVER_LISTEN_PORT);
        canServer.start();
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

                for (File plugin : files != null ? files : new File[0]) {
                    if (plugin.isFile() && plugin.getName().endsWith("jar")) {
                        pInfo("(PLUGIN) " + FIND_PLUGIN + plugin.getName());
                        Object messageReceivedListener = JarUtils.loadJar(plugin.getAbsolutePath(), "com.iot.nero.smartcan.plugin.impl.MessageReceivedListener");
                        pInfo("(EXTENSION) " + messageReceivedListener);
                        Object smartFaultListener = JarUtils.loadJar(plugin.getAbsolutePath(), "com.iot.nero.smartcan.plugin.impl.SmartFaultListener");
                        pInfo("(EXTENSION) " + smartFaultListener);
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


        // init log table
        try {
            dataBase.createTable(ConfigFactory.getConfig().getLogTableName(),columns);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // database ticker
        Timer timer = new Timer();

        // tick database
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    dataBase.tick();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 200000 , ConfigFactory.getConfig().getDbTickInterval());

    }

    public void start() {
        CONSTANT.printNdfsInfo();
        try {
            runListener();
        } catch (IOException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
