/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class SmartFaultRequestMessage {

	@Component(0)
	public Long msgCnt;

	@Component(1)
	public byte[] token;

	@Component(2)
	public byte[] vid;

	@Component(3)
	public byte[] ftype;

	@Component(4)
	public byte[] fcode;

	@Component(5)
	public Double ino;

	@Component(6)
	public byte[] timestamp;

	@Component(7)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartFaultRequestMessage)){
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

	public static SmartFaultRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartFaultRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65599);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartFaultRequestMessage.class);
		AsnConverter msgCntConverter = LongConverter.INSTANCE;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter vidConverter = OctetStringConverter.INSTANCE;
		AsnConverter ftypeConverter = OctetStringConverter.INSTANCE;
		AsnConverter fcodeConverter = OctetStringConverter.INSTANCE;
		AsnConverter inoConverter = DoubleConverter.INSTANCE;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, tokenConverter, vidConverter, ftypeConverter, fcodeConverter, inoConverter, timestampConverter, syncNumConverter });
	}


}
