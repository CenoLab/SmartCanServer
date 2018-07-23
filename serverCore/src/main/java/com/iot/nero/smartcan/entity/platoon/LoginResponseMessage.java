/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;
import java.util.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class LoginResponseMessage {

	@Component(0)
	public Long msgCnt;

	@Component(1)
	public byte[] vid;

	@Component(2)
	public Boolean loginResult;

	@Component(3)
	public byte[] token;

	@Component(4)
	public byte[] errorCode;

	@Component(5)
	public Vector<CollectConfigMessage> collectConfs;

	@Component(6)
	public byte[] timestamp;

	@Component(7)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof LoginResponseMessage)){
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

	public static LoginResponseMessage ber_decode(InputStream in) throws IOException {
		return (LoginResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65544);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(LoginResponseMessage.class);
		AsnConverter msgCntConverter = LongConverter.INSTANCE;
		AsnConverter vidConverter = OctetStringConverter.INSTANCE;
		AsnConverter loginResultConverter = BooleanConverter.INSTANCE;
		AsnConverter tokenConverter = OctetStringConverter.INSTANCE;
		AsnConverter errorCodeConverter = ResponseErrorCode.CONV;
		AsnConverter collectConfsConverter = new VectorConverter(CollectConfigMessage.CONV);
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, vidConverter, loginResultConverter, tokenConverter, errorCodeConverter, collectConfsConverter, timestampConverter, syncNumConverter });
	}


}
