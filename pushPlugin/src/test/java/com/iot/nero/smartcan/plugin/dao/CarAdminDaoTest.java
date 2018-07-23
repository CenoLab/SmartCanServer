package com.iot.nero.smartcan.plugin.dao;


import com.iot.nero.smartcan.plugin.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarAdminDaoTest {

    @Test
    public void getCarAdminAll() {

        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        CarAdminDao carAdminDao=sqlSession.getMapper(CarAdminDao.class);

        System.out.println(carAdminDao.getCarAdminAll("s"));
    }

    @Test
    public void getUserById() {
    }
}