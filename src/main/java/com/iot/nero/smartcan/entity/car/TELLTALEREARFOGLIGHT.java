/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import javax.validation.constraints.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

import com.iot.nero.smartcan.entity.Platoon;
public enum TELLTALEREARFOGLIGHT {
	inactive(0),
	active(1);

	public static TELLTALEREARFOGLIGHT valueOf(int value){
		TELLTALEREARFOGLIGHT[] values = values();
		for(int i=0;i<values.length;i++){
			if(values[i].value==value){
				return values[i];
			}
		}
		 throw new IllegalArgumentException("No enum const value for " + value);
	}


	private int value;

	private TELLTALEREARFOGLIGHT(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static TELLTALEREARFOGLIGHT ber_decode(InputStream in) throws IOException {
		return (TELLTALEREARFOGLIGHT)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65564);

	public final static AsnConverter CONV;

	static {
		CONV = new ReflectionEnumeratedConverter(TELLTALEREARFOGLIGHT.class);
	}


}
