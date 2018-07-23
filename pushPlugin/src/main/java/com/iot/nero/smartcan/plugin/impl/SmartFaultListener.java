package com.iot.nero.smartcan.plugin.impl;

import com.iot.nero.smartcan.entity.platoon.SmartFaultRequestMessage;
import com.iot.nero.smartcan.plugin.dao.CarAdminDao;
import com.iot.nero.smartcan.plugin.entity.CarAdmin;
import com.iot.nero.smartcan.plugin.entity.User;
import com.iot.nero.smartcan.plugin.utils.AiyunSDK;
import com.iot.nero.smartcan.plugin.utils.SqlSessionFactoryUtil;
import com.iot.nero.smartcan.spi.OnSmartFaultListener;
import com.iot.nero.smartcan.utils.bytes.ByteUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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

        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        CarAdminDao carAdminDao = sqlSession.getMapper(CarAdminDao.class);
        List<CarAdmin> vidUserList = carAdminDao.getCarAdminAll(vidString);
        if (!vidUserList.isEmpty()) {
            for (CarAdmin carAdmin : vidUserList) {
                if (carAdmin.getIdEmailOpen() == 1 || carAdmin.getIsPhoneOpen() == 1) {
                    User user = carAdminDao.getUserById(carAdmin.getUserId());

                        if (carAdmin.getIsPhoneOpen() == 1) {

                        }
                        if (carAdmin.getIdEmailOpen() == 1) {
                            AiyunSDK.sendMail(user.getuEmail(),vidString,String.valueOf(smartFaultRequestMessage.fcode));
                            pInfo("(PUSH_PLUGIN) MAIL TO:"+user.getuEmail());
                        }

                }
            }
        }

        sqlSession.commit();
        sqlSession.close();
    }
}
