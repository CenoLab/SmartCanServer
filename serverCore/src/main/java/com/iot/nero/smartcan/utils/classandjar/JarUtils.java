package com.iot.nero.smartcan.utils.classandjar;

import com.iot.nero.smartcan.core.Protocol;
import com.iot.nero.smartcan.spi.classloader.SPIClassLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   6:23 PM
 */
public class JarUtils {

    static Map<String, Class<?>> clzMap = new HashMap<>();


    /**
     * 动态加载Jar包到内存中
     */
    public static Object loadJar(String jarFile, String className) {
        try {
            File file = new File(jarFile);
            if (!file.exists()) {
                throw new RuntimeException(jarFile + "不存在");
            }

            try {
                try (URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, Thread.currentThread().getContextClassLoader())) {
                    Class<?> clazz = loader.loadClass(className);

                    Class<?> cls = clzMap.get(jarFile + className);
                    if (cls == null) {
                        clzMap.put(jarFile + className, clazz);
                        return clazz.newInstance();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Class<?> getClass(String jarFile, String className) throws IllegalAccessException, InstantiationException {
        Class<?> cls = clzMap.get(jarFile + className);
        return cls;
    }
}
