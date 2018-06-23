/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.type.AsnType;
import com.iot.nero.smartcan.entity.Platoon;

public enum TCUTGSMode {
	normal(0),
	winter(1),
	performance(2),
	econonmy(3),
	invalid(4);

	public static TCUTGSMode valueOf(int value){
		TCUTGSMode[] values = values();
		for(int i=0;i<values.length;i++){
			if(values[i].value==value){
				return values[i];
			}
		}
		 throw new IllegalArgumentException("No enum const value for " + value);
	}


	private int value;

	private TCUTGSMode(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static TCUTGSMode ber_decode(InputStream in) throws IOException {
		return (TCUTGSMode)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65588);

	public final static AsnConverter CONV;

	static {
		CONV = new ReflectionEnumeratedConverter(TCUTGSMode.class);
	}


}
