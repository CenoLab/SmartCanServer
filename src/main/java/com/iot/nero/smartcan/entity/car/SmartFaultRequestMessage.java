/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import javax.validation.constraints.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import com.iot.nero.smartcan.entity.Platoon;

public class SmartFaultRequestMessage {

	@NotNull
	@Component(0)
	public Long msgCnt;

	@NotNull
	@Component(1)
	public byte[] token;

	@NotNull
	@Component(2)
	public byte[] ftype;

	@NotNull
	@Component(3)
	public byte[] fcode;

	@NotNull
	@Component(4)
	public Double ino;

	@NotNull
	@Component(5)
	public Position3D positon;

	@NotNull
	@Component(6)
	public byte[] timestamp;

	@NotNull
	@Component(7)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartFaultRequestMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartFaultRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartFaultRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65613);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartFaultRequestMessage.class);
		AsnConverter msgCntConverter = MsgCount.CONV;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter ftypeConverter = FaultType.CONV;
		AsnConverter fcodeConverter = FaultCode.CONV;
		AsnConverter inoConverter = InnerNo.CONV;
		AsnConverter positonConverter = Position3D.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCntConverter, tokenConverter, ftypeConverter, fcodeConverter, inoConverter, positonConverter, timestampConverter, syncNumConverter });
	}


}
