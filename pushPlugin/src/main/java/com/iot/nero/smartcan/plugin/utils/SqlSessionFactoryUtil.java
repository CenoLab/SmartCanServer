package com.iot.nero.smartcan.plugin.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    static SqlSessionFactory factory = null;
    static InputStream is = null;

    static {//用一个静态块读取配置文件，获取返回的文件流。静态块在编译时就初始化
        try {
            is = new FileInputStream(System.getProperty("user.dir") + "/plugin/pushPlugin/config/mybatis-config.xml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //采用单例模式来保证工厂创建唯一
    public static SqlSessionFactory getSqlSessionFactory() {
        if (factory == null) {
        //创建SqlsessionFactory工厂
            factory = new SqlSessionFactoryBuilder().build(is);
        }
        return factory;
    }

    //获取SqlSession对象
    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }
}
