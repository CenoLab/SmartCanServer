/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import java.util.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.type.AsnType;
import com.iot.nero.smartcan.entity.Platoon;

public class WiperList {

	public static void ber_encode(Vector<Wiper> object, OutputStream out) throws IOException {
		TYPE.encode(object, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Vector<Wiper> ber_decode(InputStream in) throws IOException {
		return (Vector<Wiper>)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65560);

	public final static AsnConverter CONV = new VectorConverter(Wiper.CONV);



}