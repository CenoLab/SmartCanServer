/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;

import com.iot.nero.smartcan.entity.Platoon;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class FRONTWIPERINTERMSWITCH {


	public static void ber_encode(Integer object, OutputStream out) throws IOException {
		TYPE.encode(object, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Integer ber_decode(InputStream in) throws IOException {
		return (Integer)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65556);

	public final static AsnConverter CONV = IntegerConverter.INSTANCE;



}
