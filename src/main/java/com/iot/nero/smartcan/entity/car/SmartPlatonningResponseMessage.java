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

public class SmartPlatonningResponseMessage {

	@NotNull
	@Component(0)
	public Long msgCount;

	@NotNull
	@Size(min=8, max=8)
	@Component(1)
	public byte[] msgid;

	@NotNull
	@Component(2)
	public Boolean msgStatus;

	@NotNull
	@Component(3)
	public byte[] errorCode;

	@NotNull
	@Component(4)
	public byte[] timestamp;

	@NotNull
	@Component(5)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartPlatonningResponseMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartPlatonningResponseMessage ber_decode(InputStream in) throws IOException {
		return (SmartPlatonningResponseMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65623);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartPlatonningResponseMessage.class);
		AsnConverter msgCountConverter = MsgCount.CONV;
		AsnConverter msgidConverter = OctetStringConverter.INSTANCE;
		AsnConverter msgStatusConverter = BooleanConverter.INSTANCE;
		AsnConverter errorCodeConverter = ResponseErrorCode.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, msgidConverter, msgStatusConverter, errorCodeConverter, timestampConverter, syncNumConverter });
	}


}
