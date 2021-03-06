/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class VehicleList {

	@Component(0)
	public byte[] vid;


	public boolean equals(Object obj) {
		if(!(obj instanceof VehicleList)){
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

	public static VehicleList ber_decode(InputStream in) throws IOException {
		return (VehicleList)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65601);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(VehicleList.class);
		AsnConverter vidConverter = OctetStringConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { vidConverter });
	}


}
