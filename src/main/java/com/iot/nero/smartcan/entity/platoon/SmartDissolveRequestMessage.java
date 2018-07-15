/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;
import java.math.*;
import java.util.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

public class SmartDissolveRequestMessage {

	@Component(0)
	public Long msgCount;

	@Component(1)
	public byte[] token;

	@Component(2)
	public byte[] id;

	@Component(3)
	public byte[] endtime;

	@Component(4)
	public Long vehicleCount;

	@Component(5)
	public Vector<VehicleList> vehicleList;

	@Component(6)
	public byte[] timestamp;

	@Component(7)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartDissolveRequestMessage)){
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

	public static SmartDissolveRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartDissolveRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65606);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartDissolveRequestMessage.class);
		AsnConverter msgCountConverter = LongConverter.INSTANCE;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter endtimeConverter = TimeStamp.CONV;
		AsnConverter vehicleCountConverter = LongConverter.INSTANCE;
		AsnConverter vehicleListConverter = new VectorConverter(VehicleList.CONV);
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, idConverter, endtimeConverter, vehicleCountConverter, vehicleListConverter, timestampConverter, syncNumConverter });
	}


}
