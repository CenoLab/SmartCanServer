package com.iot.nero.smartcan.spi.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/17
 * Time   1:01 PM
 */
public class SPIClassLoader extends URLClassLoader {

    public SPIClassLoader(URL[] urls) {
        super(urls);
    }

    public SPIClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addJar(URL url) {
        this.addURL(url);
    }

}
