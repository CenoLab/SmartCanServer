/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class SmartCtrlFeedBackResponseMessage {

	@Component(0)
	public Long msgCnt;

	@Component(1)
	public Boolean msgStatus;

	@Component(2)
	public byte[] errorCode;

	@Component(3)
	public byte[] timestamp;

	@Component(4)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartCtrlFeedBackResponseMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void print(PrintStream out) {
		TYPE.print(this, CONV, out);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartCtrlFeedBackResponseMessage ber_decode(InputStream in) throws IOException {
		return (SmartCtrlFeedBackResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65598);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartCtrlFeedBackResponseMessage.class);
		AsnConverter msgCntConverter = LongConverter.INSTANCE;
		AsnConverter msgStatusConverter = BooleanConverter.INSTANCE;
		AsnConverter errorCodeConverter = ResponseErrorCode.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, msgStatusConverter, errorCodeConverter, timestampConverter, syncNumConverter });
	}


}