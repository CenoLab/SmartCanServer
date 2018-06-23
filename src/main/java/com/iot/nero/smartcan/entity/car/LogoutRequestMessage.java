/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import javax.validation.constraints.*;

import com.iot.nero.smartcan.entity.Platoon;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class LogoutRequestMessage {

	@NotNull
	@Component(0)
	public Long msgCount;

	@NotNull
	@Component(1)
	public byte[] token;

	@NotNull
	@Component(2)
	public byte[] timestamp;

	@NotNull
	@Component(3)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof LogoutRequestMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static LogoutRequestMessage ber_decode(InputStream in) throws IOException {
		return (LogoutRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65553);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(LogoutRequestMessage.class);
		AsnConverter msgCountConverter = MsgCount.CONV;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, timestampConverter, syncNumConverter });
	}


}