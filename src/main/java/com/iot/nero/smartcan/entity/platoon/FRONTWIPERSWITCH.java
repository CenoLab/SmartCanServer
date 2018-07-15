/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

public enum FRONTWIPERSWITCH {
	off(0),
	auto(1),
	low(2),
	high(3),
	mist(4);

	public static FRONTWIPERSWITCH valueOf(int value){
		FRONTWIPERSWITCH[] values = values();
		for(int i=0;i<values.length;i++){
			if(values[i].value==value){
				return values[i];
			}
		}
		 throw new IllegalArgumentException("No enum const value for " + value);
	}


	private int value;

	private FRONTWIPERSWITCH(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}

	public void print(PrintStream out) {
		TYPE.print(this, CONV, out);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static FRONTWIPERSWITCH ber_decode(InputStream in) throws IOException {
		return (FRONTWIPERSWITCH)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65549);

	public final static AsnConverter CONV;

	static {
		CONV = new ReflectionEnumeratedConverter(FRONTWIPERSWITCH.class);
	}


}
