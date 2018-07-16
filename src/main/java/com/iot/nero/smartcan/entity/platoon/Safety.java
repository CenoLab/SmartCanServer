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

public class Safety {

	@Component(0)
	public ABSOPERATING absoperating;

	@Component(1)
	public ESPOPERATING espoperating;

	@Component(2)
	public TCSOPERATING tcsoperating;

	@Component(3)
	public EPSSTA epssta;

	@Component(4)
	public TCUTGSMode tcutgsmode;

	@Component(5)
	public Integer tcucurrentgearposition;


	public boolean equals(Object obj) {
		if(!(obj instanceof Safety)){
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

	public static Safety ber_decode(InputStream in) throws IOException {
		return (Safety)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65575);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Safety.class);
		AsnConverter absoperatingConverter = ABSOPERATING.CONV;
		AsnConverter espoperatingConverter = ESPOPERATING.CONV;
		AsnConverter tcsoperatingConverter = TCSOPERATING.CONV;
		AsnConverter epsstaConverter = EPSSTA.CONV;
		AsnConverter tcutgsmodeConverter = TCUTGSMode.CONV;
		AsnConverter tcucurrentgearpositionConverter = IntegerConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { absoperatingConverter, espoperatingConverter, tcsoperatingConverter, epsstaConverter, tcutgsmodeConverter, tcucurrentgearpositionConverter });
	}


}