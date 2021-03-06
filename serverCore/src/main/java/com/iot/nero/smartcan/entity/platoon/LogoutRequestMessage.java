/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class LogoutRequestMessage {

	@Component(0)
	public Long msgCount;

	@Component(1)
	public byte[] token;

	@Component(2)
	public byte[] timestamp;

	@Component(3)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof LogoutRequestMessage)){
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

	public static LogoutRequestMessage ber_decode(InputStream in) throws IOException {
		return (LogoutRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65547);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(LogoutRequestMessage.class);
		AsnConverter msgCountConverter = LongConverter.INSTANCE;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, timestampConverter, syncNumConverter });
	}


}
