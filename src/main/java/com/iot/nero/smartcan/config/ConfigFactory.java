package com.iot.nero.smartcan.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:53
 */
public class ConfigFactory {

    private static Config config;

    public static Config getConfig(){
        if(config!=null){
            return config;
        }
        Map<String,String> conf = new HashMap<>();
        config = new Config(conf);
        return config;
    }
}
