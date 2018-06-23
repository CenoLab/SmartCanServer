/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.validation.constraints.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

import com.iot.nero.smartcan.entity.Platoon;
public class SmartPlatonningRequestMessage {

	@NotNull
	@Component(0)
	public Long msgCount;

	@NotNull
	@Component(1)
	public byte[] token;

	@NotNull
	@Size(min=8, max=8)
	@Component(2)
	public byte[] id;

	@NotNull
	@Component(3)
	public Long vehicleCount;

	@NotNull
	@Component(4)
	public Vector<byte[]> vehicleList;

	@NotNull
	@Component(5)
	public Double splatoonpeed;

	@NotNull
	@Component(6)
	public Position3D platoonposition;

	@NotNull
	@Component(7)
	public Double platoonlength;

	@NotNull
	@Component(8)
	public byte[] timestamp;

	@NotNull
	@Component(9)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartPlatonningRequestMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartPlatonningRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartPlatonningRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65623);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartPlatonningRequestMessage.class);
		AsnConverter msgCountConverter = MsgCount.CONV;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter vehicleCountConverter = LongConverter.INSTANCE;
		AsnConverter vehicleListConverter = VehicleList.CONV;
		AsnConverter splatoonpeedConverter = FLOAT.CONV;
		AsnConverter platoonpositionConverter = Position3D.CONV;
		AsnConverter platoonlengthConverter = FLOAT.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, idConverter, vehicleCountConverter, vehicleListConverter, splatoonpeedConverter, platoonpositionConverter, platoonlengthConverter, timestampConverter, syncNumConverter });
	}


}
