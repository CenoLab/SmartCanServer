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

public class Other {

	@NotNull
	@Component(0)
	public Double outsidetemp;

	@NotNull
	@Component(1)
	public HANDBRAKE handbrake;

	@NotNull
	@Component(2)
	public AutoHorn autohorn;


	public boolean equals(Object obj) {
		if(!(obj instanceof Other)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Other ber_decode(InputStream in) throws IOException {
		return (Other)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65594);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Other.class);
		AsnConverter outsidetempConverter = DoubleConverter.INSTANCE;
		AsnConverter handbrakeConverter = HANDBRAKE.CONV;
		AsnConverter autohornConverter = AutoHorn.CONV;
		CONV.setComponentConverters(new AsnConverter[] { outsidetempConverter, handbrakeConverter, autohornConverter });
	}


}