/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import java.math.*;
import javax.validation.constraints.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

import com.iot.nero.smartcan.entity.Platoon;
public class SmartFaultResponseMessage {

	@NotNull
	@Component(0)
	public Long msgCnt;

	@NotNull
	@Component(1)
	public Boolean msgStatus;

	@NotNull
	@Component(2)
	public byte[] errorCode;

	@NotNull
	@Component(3)
	public byte[] timestamp;

	@NotNull
	@Component(4)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartFaultResponseMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartFaultResponseMessage ber_decode(InputStream in) throws IOException {
		return (SmartFaultResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65615);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartFaultResponseMessage.class);
		AsnConverter msgCntConverter = MsgCount.CONV;
		AsnConverter msgStatusConverter = BooleanConverter.INSTANCE;
		AsnConverter errorCodeConverter = ResponseErrorCode.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, msgStatusConverter, errorCodeConverter, timestampConverter, syncNumConverter });
	}


}
