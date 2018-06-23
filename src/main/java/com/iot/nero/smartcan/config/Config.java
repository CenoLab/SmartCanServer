package com.iot.nero.smartcan.config;

import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:49
 */
public class Config {
    private Map<String,String> config;

    public Config(Map<String, String> config) {
        this.config = config;
    }

    public String getConfig(String key){
        return config.get(key);
    }

    public void  setConfig(String key,String value){
        config.put(key,value);
    }

}
