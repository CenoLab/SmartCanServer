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
public class RecongnizeObject {

	@NotNull
	@Size(min=8, max=8)
	@Component(0)
	public byte[] id;

	@NotNull
	@Component(1)
	public XTypeEnum type;

	@NotNull
	@Component(2)
	public Double x;

	@NotNull
	@Component(3)
	public Double y;

	@NotNull
	@Component(4)
	public Double speedx;

	@NotNull
	@Component(5)
	public Double speedy;

	@NotNull
	@Component(6)
	public Double width;

	@NotNull
	@Component(7)
	public Double length;

	@NotNull
	@Component(8)
	public Double heigth;

	@NotNull
	@Component(9)
	public Double longAngle;

	@NotNull
	@Component(10)
	public Double latAngle;

	@NotNull
	@Component(11)
	public Double yawAngle;


	public boolean equals(Object obj) {
		if(!(obj instanceof RecongnizeObject)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static RecongnizeObject ber_decode(InputStream in) throws IOException {
		return (RecongnizeObject)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65598);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(RecongnizeObject.class);
		AsnConverter idConverter = OctetStringConverter.INSTANCE;
		AsnConverter typeConverter = XTypeEnum.CONV;
		AsnConverter xConverter = FLOAT.CONV;
		AsnConverter yConverter = FLOAT.CONV;
		AsnConverter speedxConverter = FLOAT.CONV;
		AsnConverter speedyConverter = FLOAT.CONV;
		AsnConverter widthConverter = FLOAT.CONV;
		AsnConverter lengthConverter = FLOAT.CONV;
		AsnConverter heigthConverter = FLOAT.CONV;
		AsnConverter longAngleConverter = ANGLE.CONV;
		AsnConverter latAngleConverter = ANGLE.CONV;
		AsnConverter yawAngleConverter = ANGLE.CONV;
		CONV.setComponentConverters(new AsnConverter[] { idConverter, typeConverter, xConverter, yConverter, speedxConverter, speedyConverter, widthConverter, lengthConverter, heigthConverter, longAngleConverter, latAngleConverter, yawAngleConverter });
	}


}
