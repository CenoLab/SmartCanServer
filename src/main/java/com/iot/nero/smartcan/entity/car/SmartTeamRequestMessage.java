/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.car;

import java.io.*;
import java.math.*;
import javax.validation.constraints.*;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.value.*;

import com.iot.nero.smartcan.entity.Platoon;
public class SmartTeamRequestMessage {

	@NotNull
	@Component(0)
	public Long msgCount;

	@NotNull
	@Component(1)
	public byte[] token;

	@NotNull
	@Size(min=8, max=8)
	@Component(2)
	public byte[] vid;

	@NotNull
	@Component(3)
	public ISFLEET isfleet;

	@NotNull
	@Size(min=8, max=8)
	@Component(4)
	public byte[] id;

	@NotNull
	@Component(5)
	public ROLE role;

	@NotNull
	@Component(6)
	public Long vehiclenum;

	@NotNull
	@Component(7)
	public Double frontdistance;

	@NotNull
	@Component(8)
	public Double speed;

	@NotNull
	@Component(9)
	public byte[] timestamp;

	@NotNull
	@Component(10)
	public Long syncNum;


	public boolean equals(Object obj) {
		if(!(obj instanceof SmartTeamRequestMessage)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static SmartTeamRequestMessage ber_decode(InputStream in) throws IOException {
		return (SmartTeamRequestMessage)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65627);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(SmartTeamRequestMessage.class);
		AsnConverter msgCountConverter = MsgCount.CONV;
		AsnConverter tokenConverter = Token.CONV;
		AsnConverter vidConverter = OctetStringConverter.INSTANCE;
		AsnConverter isfleetConverter = ISFLEET.CONV;
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter roleConverter = ROLE.CONV;
		AsnConverter vehiclenumConverter = LongConverter.INSTANCE;
		AsnConverter frontdistanceConverter = FLOAT.CONV;
		AsnConverter speedConverter = FLOAT.CONV;
		AsnConverter timestampConverter = TimeStamp.CONV;
		AsnConverter syncNumConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { msgCountConverter, tokenConverter, vidConverter, isfleetConverter, idConverter, roleConverter, vehiclenumConverter, frontdistanceConverter, speedConverter, timestampConverter, syncNumConverter });
	}


}