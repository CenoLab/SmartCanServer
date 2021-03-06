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

public class SmartPlatonningRequestMessage {

	@Component(0)
	public Long msgCount;

	@Component(1)
	public byte[] token;

	@Component(2)
	public byte[] id;

	@Component(3)
	public Long vehicleCount;

	@Component(4)
	public Vector<VehicleList> vehicleList;

	@Component(5)
	public Double splatoonpeed;

	@Component(6)
	public Position2D platoonposition;

	@Component(7)
	public Double platoonlength;

	@Component(8)
	public byte[] timestamp;

	@Component(9)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartPlatonningRequestMessage)){
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

	public static SmartPlatonningRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartPlatonningRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65608);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartPlatonningRequestMessage.class);
		AsnConverter msgCountConverter = LongConverter.INSTANCE;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter vehicleCountConverter = LongConverter.INSTANCE;
		AsnConverter vehicleListConverter = new VectorConverter(VehicleList.CONV);
		AsnConverter splatoonpeedConverter = DoubleConverter.INSTANCE;
		AsnConverter platoonpositionConverter = Position2D.CONV;
		AsnConverter platoonlengthConverter = DoubleConverter.INSTANCE;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, idConverter, vehicleCountConverter, vehicleListConverter, splatoonpeedConverter, platoonpositionConverter, platoonlengthConverter, timestampConverter, syncNumConverter });
	}


}
