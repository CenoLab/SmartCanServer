package com.iot.nero.smartcan.plugin.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AiyunSDK {

    private static String FAULT_EMAIL_TEMPLETE = "车辆 __vid__ 出现了 __fault__ 异常，请及时处理。 /n __time__";

    public static Boolean sendMail(String email,String vid,String fault) {

        String verifyEmail = FAULT_EMAIL_TEMPLETE.replaceAll("__vid__", vid);
        verifyEmail = verifyEmail.replaceAll("__fault__", fault);
        verifyEmail = verifyEmail.replaceAll("__time__", String.valueOf(System.currentTimeMillis()));

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIr1CEAT80ffuC", "HUiji3BDDW1lTiO4Jlj8kByppjTXZG");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("sso@support.cenocloud.com");
            request.setFromAlias("SmartCanServer");
            request.setAddressType(1);
            request.setTagName("verify");
            request.setReplyToAddress(true);
            request.setToAddress(email);
            request.setSubject("车辆"+vid+"异常");
            request.setHtmlBody(verifyEmail);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);

            return true;
        } catch (ClientException e) {
            return false;
        }
    }

}
