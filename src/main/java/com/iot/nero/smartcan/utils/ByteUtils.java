package com.iot.nero.smartcan.utils;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/6
 * Time   10:34 AM
 */
public class ByteUtils {
    public static String bytesToString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:bytes){
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }
}
