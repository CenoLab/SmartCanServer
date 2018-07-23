package com.iot.nero.smartcan.core;

import com.iot.nero.smartcan.constant.CONSTANT;
import com.iot.nero.smartcan.exceptions.PackageBrokenException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.iot.nero.smartcan.constant.CONSTANT.pInfo;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午3:13
 * <p>
 * 0	        起始符		    STRING	固定为ASCII字符##, '0x23,0x23,' 表示
 * 3	        命令单元	命令标识	2BYTE	命令单元详见 表3
 * 5	        应答标志	        BYTE
 * <p>
 * 6	        唯一识别码		STRING	当传输车辆信息时, 应使用车辆VIN(17位), 其字码应符合GB16735的规定. 如传输其他数据, 则使用唯一自定义编码
 * 23	        数据单元加密方式	BYTE	"0x01:数据不加密 ; 0x02: 使用RSA算法加密; 0x03:使用AES128算法加密; 0xFE: 异常;  0xFF:无效. 其他预留"
 * 32	        数据单元长度		WORD	本条消息的总字节数(0~65535)
 * 33	        数据单元		　	        ASN_1格式和定义详见 第4章
 * 倒数第1位	    校验码		    BYTE	使用 BCC 校验法.校验范围从命令单元的第一个字节开始, 同后一字节异或, 直到校验码前一字节为止, 校验码占用一个字节, 当数据单元存在加密时, 应先加密后校验, 先校验后解密
 */
public class Protocol {
    public byte[] startSymbol;
    public byte[] commandUnit = new byte[2];
    public byte[] inditicalCode = new byte[17];
    public byte dataUnitEncryptMethod;
    public short dataUnitLength;          //& 0xffff
    public byte[] dataUnit;
    public byte checkCode;

    public Protocol decode(byte[] data) throws PackageBrokenException {

        pInfo("(RECEIVED) ");
        for (byte b : data) {
            System.out.print(" " + Integer.toHexString(b));
        }
        System.out.println();

        for (Integer i = 0; i < 17; i++) {
            inditicalCode[i] = 0x00;
        }

        if (data[0] == 0x23 && data[1] == 0x23) {
            // 命令单元
            commandUnit[0] = data[2];
            commandUnit[1] = data[3];
            int i;

            for (i = 4; i < 21; i++) {
                inditicalCode[i - 4] = (byte) data[i];
            }

            i = 21;
            dataUnitEncryptMethod = data[i];//"0x01:数据不加密 ; 0x02: 使用RSA算法加密; 0x03:使用AES128算法加密; 0xFE: 异常;  0xFF:无效.


            ByteBuffer bb = ByteBuffer.allocate(2);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            bb.put(data[++i]);
            bb.put(data[++i]);
            dataUnitLength = bb.getShort(0);


            int length = 0;
            dataUnit = new byte[dataUnitLength];
            while (length < dataUnitLength) {
                dataUnit[length] = data[++i];
                length++;
            }

            checkCode = data[data.length - 1];
            //CRC校验
            int index;
            byte bcc = data[2];

            for (index = 3; index < data.length - 1; index++) {
                bcc ^= data[index];
            }

            if (bcc == checkCode) {
                return this;
            } else {
                throw new PackageBrokenException(CONSTANT.PACKAGE_BROKEN);
            }
        }
        throw new PackageBrokenException(CONSTANT.START_SYMBOL_INCORRECT);
    }

    public byte[] toByte() {
        byte[] allData = new byte[2 + 2 + 17 + 1 + 2 + dataUnitLength + 1];

        allData[0] = this.startSymbol[0];
        allData[1] = this.startSymbol[1];

        allData[2] = this.commandUnit[0];
        allData[3] = this.commandUnit[1];


        for (int i = 0; i < 17; i++) {
            allData[4 + i] = this.inditicalCode[i];
        }

        allData[21] = this.dataUnitEncryptMethod;

        allData[22] = getBytes(this.dataUnitLength, true)[1];
        allData[23] = getBytes(this.dataUnitLength, true)[0];


        for (int i = 0; i < this.dataUnit.length; i++) {
            allData[24 + i] = this.dataUnit[i];
        }

        byte bcc = allData[2];
        for (int index = 3; index < allData.length - 1; index++) {
            bcc ^= allData[index];
        }

        allData[allData.length - 1] = bcc;


        return allData;
    }

    public byte[] getBytes(short s, boolean asc) {
        byte[] buf = new byte[2];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        return buf;
    }


    public byte[] getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(byte[] startSymbol) {
        this.startSymbol = startSymbol;
    }

    public byte[] getCommandUnit() {
        return commandUnit;
    }

    public void setCommandUnit(byte[] commandUnit) {
        this.commandUnit = commandUnit;
    }

    public byte[] getInditicalCode() {
        return inditicalCode;
    }

    public void setInditicalCode(byte[] inditicalCode) {
        this.inditicalCode = inditicalCode;
    }

    public byte getDataUnitEncryptMethod() {
        return dataUnitEncryptMethod;
    }

    public void setDataUnitEncryptMethod(byte dataUnitEncryptMethod) {
        this.dataUnitEncryptMethod = dataUnitEncryptMethod;
    }

    public short getDataUnitLength() {
        return dataUnitLength;
    }

    public void setDataUnitLength(short dataUnitLength) {
        this.dataUnitLength = dataUnitLength;
    }

    public byte[] getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(byte[] dataUnit) {
        this.dataUnit = dataUnit;
    }

    public byte getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(byte checkCode) {
        this.checkCode = checkCode;
    }
}
