/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.type.AsnType;
import com.iot.nero.smartcan.entity.Platoon;

public enum EPSSTA {
	readiness(0),
	normal(1),
	fault(2),
	void_(3);

	public static EPSSTA valueOf(int value){
		EPSSTA[] values = values();
		for(int i=0;i<values.length;i++){
			if(values[i].value==value){
				return values[i];
			}
		}
		 throw new IllegalArgumentException("No enum const value for " + value);
	}


	private int value;

	private EPSSTA(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static EPSSTA ber_decode(InputStream in) throws IOException {
		return (EPSSTA)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65587);

	public final static AsnConverter CONV;

	static {
		CONV = new ReflectionEnumeratedConverter(EPSSTA.class);
	}


}
