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

public class SmartRecognizeRequestMessage {

	@Component(0)
	public Long msgCnt;

	@Component(1)
	public byte[] token;

	@Component(2)
	public byte[] vid;

	@Component(3)
	public Vector<RecongnizeObject> recongnizeobject;

	@Component(4)
	public Position3D position;

	@Component(5)
	public byte[] timestamp;

	@Component(6)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartRecognizeRequestMessage)){
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

	public static SmartRecognizeRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartRecognizeRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65586);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartRecognizeRequestMessage.class);
		AsnConverter msgCntConverter = LongConverter.INSTANCE;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter vidConverter = OctetStringConverter.INSTANCE;
		AsnConverter recongnizeobjectConverter = new VectorConverter(RecongnizeObject.CONV);
		AsnConverter positionConverter = Position3D.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, tokenConverter, vidConverter, recongnizeobjectConverter, positionConverter, timestampConverter, syncNumConverter });
	}


}
