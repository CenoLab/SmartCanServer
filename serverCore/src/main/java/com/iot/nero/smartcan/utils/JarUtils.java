package com.iot.nero.smartcan.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   6:23 PM
 */
public class JarUtils {
    protected static Method addURL = null ;
    static{
        try {
            addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class }) ;
            addURL.setAccessible(true);
        } catch (Exception e) {
        }
    }

    /**
     * 动态加载Jar包到内存中
     * */
    public static Object loadJar(String jarFile, String className) {
        try {
            File file = new File( jarFile );
            if (!file.exists()) {
                throw new RuntimeException(jarFile + "不存在");
            }
            addURL.invoke(ClassLoader.getSystemClassLoader(), new Object[] { file.toURI().toURL() });
            return Class.forName( className ,false , ClassLoader.getSystemClassLoader() ).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
