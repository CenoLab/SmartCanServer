package com.iot.nero.smartcan.factory;


import com.iot.nero.smartcan.config.Config;
import com.iot.nero.smartcan.config.ConfigLoader;

import java.io.IOException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/27
 * Time   4:14 PM
 */
public class ConfigFactory {

    private static Config config;

    public static Config getConfig() throws IOException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        if(config!=null){
            return config;
        }
        config = ConfigLoader.loadConfig();
        return config;
    }
}
