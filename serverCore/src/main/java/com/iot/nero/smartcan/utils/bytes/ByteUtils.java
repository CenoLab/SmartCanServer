package com.iot.nero.smartcan.utils.bytes;

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


    public static byte[] longToBytes(long v) {

        byte[] writeBuffer = new byte[8];

        writeBuffer[0] = (byte) (v >>> 56);
        writeBuffer[1] = (byte) (v >>> 48);
        writeBuffer[2] = (byte) (v >>> 40);
        writeBuffer[3] = (byte) (v >>> 32);
        writeBuffer[4] = (byte) (v >>> 24);
        writeBuffer[5] = (byte) (v >>> 16);
        writeBuffer[6] = (byte) (v >>> 8);
        writeBuffer[7] = (byte) (v >>> 0);

        return writeBuffer;
    }
}
