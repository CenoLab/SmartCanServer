/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class LogoutResponseMessage {

	@Component(0)
	public Boolean logoutResult;

	@Component(1)
	public byte[] timestamp;

	@Component(2)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof LogoutResponseMessage)){
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

	public static LogoutResponseMessage ber_decode(InputStream in) throws IOException {
		return (LogoutResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65548);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(LogoutResponseMessage.class);
		AsnConverter logoutResultConverter = BooleanConverter.INSTANCE;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { logoutResultConverter, timestampConverter, syncNumConverter });
	}


}
