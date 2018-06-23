package com.iot.nero.smartcan.config;

import com.iot.nero.smartcan.utils.FileUtil;

import java.io.IOException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:49
 */
public class ConfigLoader {

    private String configFilePath = System.getProperty("user.dir")+System.lineSeparator()+"config/config.properties";

    public Config loadConfig() throws IOException {
        FileUtil fileUtil = new FileUtil();
//        fileUtil.doRead(new RandomAccessFile(configFilePath,"rw"));
        return null;
    }

}
