package com.iot.nero.smartcan.plugin.impl;

import com.iot.nero.smartcan.entity.platoon.SmartFaultRequestMessage;
import com.iot.nero.smartcan.plugin.utils.SqlSessionFactoryUtil;
import com.iot.nero.smartcan.spi.OnSmartFaultListener;
import com.iot.nero.smartcan.utils.bytes.ByteUtils;
import org.apache.ibatis.session.SqlSession;

import static com.iot.nero.smartcan.constant.CONSTANT.pInfo;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/16
 * Time   2:28 PM
 */
public class SmartFaultListener implements OnSmartFaultListener {
    @Override
    public void onFault(SmartFaultRequestMessage smartFaultRequestMessage) {

        pInfo("(PUSH_PLUGIN) 车辆异常推送触发.");
        // 推送 短信 或者 邮件 通知
        byte[] vid = smartFaultRequestMessage.vid;
        String vidString = ByteUtils.bytesToString(vid);

        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        List<XX> vidUserList=sqlSession.select("");

        for(XX xx:vidUserList){

        }

        sqlSession.commit();
        sqlSession.close();
    }
}
