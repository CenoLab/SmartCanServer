package com.iot.nero.smartcan.entity;

import java.util.Vector;

import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.type.AsnModule;
import org.asnlab.asndt.runtime.conv.*;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午3:13
 *
 * 0	        起始符		    STRING	固定为ASCII字符##, '0x23,0x23,' 表示
 * 3	        命令单元	命令标识	2BYTE	命令单元详见 表3
 * 5	        应答标志	        BYTE
 *
 * 6	        唯一识别码		STRING	当传输车辆信息时, 应使用车辆VIN(17位), 其字码应符合GB16735的规定. 如传输其他数据, 则使用唯一自定义编码
 * 23	        数据单元加密方式	BYTE	"0x01:数据不加密 ; 0x02: 使用RSA算法加密; 0x03:使用AES128算法加密; 0xFE: 异常;  0xFF:无效. 其他预留"
 * 32	        数据单元长度		WORD	本条消息的总字节数(0~65535)
 * 33	        数据单元		　	        ASN_1格式和定义详见 第4章
 * 倒数第1位	    校验码		    BYTE	使用 BCC 校验法.校验范围从命令单元的第一个字节开始, 同后一字节异或, 直到校验码前一字节为止, 校验码占用一个字节, 当数据单元存在加密时, 应先加密后校验, 先校验后解密
 */
public class Platoon extends AsnModule {

    public final static Platoon instance = new Platoon();

    public Platoon() {
        super(Platoon.class);
    }

    public byte[] enPacking(Platoon data){
        return null;
    }

    public static AsnType type(int id) {
        return instance.getType(id);
    }

    public static Object value(int valueId, AsnConverter converter) {
        return instance.getValue(valueId, converter);
    }

    public static Object object(int objectId, AsnConverter converter) {
        return instance.getObject(objectId, converter);
    }

    public static Vector objectSet(int objectSetId, AsnConverter converter) {
        return instance.getObjectSet(objectSetId, converter);
    }


    public static Platoon getInstance() {
        return instance;
    }
}
