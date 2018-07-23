package com.iot.nero.smartcan.plugin.dao;

import com.iot.nero.smartcan.plugin.entity.CarAdmin;
import com.iot.nero.smartcan.plugin.entity.User;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface CarAdminDao {

    @Select("select user_is as userId,unique_id as uniqueId,is_mail_open as isMailOpen, is_phone_open as isPhoneOpen from car_admin where unique_id = #{uniqueId}")
    List<CarAdmin> getCarAdminAll(String uniqueId);

    @Select("select id as id,u_name as uName,u_email as uEmail,u_phone as uPhone from user where id = #{id}")
    User  getUserById(Integer id);
}
