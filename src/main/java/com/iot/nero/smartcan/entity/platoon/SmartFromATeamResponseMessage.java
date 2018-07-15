/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;
import java.math.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

public class SmartFromATeamResponseMessage {

	@Component(0)
	public Long msgCount;

	@Component(1)
	public byte[] msgid;

	@Component(2)
	public Boolean msgStatus;

	@Component(3)
	public byte[] id;

	@Component(4)
	public byte[] errorCode;

	@Component(5)
	public byte[] timestamp;

	@Component(6)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartFromATeamResponseMessage)){
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

	public static SmartFromATeamResponseMessage ber_decode(InputStream in) throws IOException {
		return (SmartFromATeamResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65603);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartFromATeamResponseMessage.class);
		AsnConverter msgCountConverter = LongConverter.INSTANCE;
		AsnConverter msgidConverter = OctetStringConverter.INSTANCE;
		AsnConverter msgStatusConverter = BooleanConverter.INSTANCE;
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter errorCodeConverter = ResponseErrorCode.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, msgidConverter, msgStatusConverter, idConverter, errorCodeConverter, timestampConverter, syncNumConverter });
	}


}
