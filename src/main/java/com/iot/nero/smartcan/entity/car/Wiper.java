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
import com.iot.nero.smartcan.entity.Platoon;

public class Wiper {

	@NotNull
	@Component(0)
	public FRONTWIPERSWITCH frontwiperwitch;

	@NotNull
	@Component(1)
	public Integer frontwiperintermswitch;

	@NotNull
	@Component(2)
	public REARWIPER rearwiper;

	@NotNull
	@Component(3)
	public FRONTWASHINGSWITCH frontwashingswitch;


	public boolean equals(Object obj) {
		if(!(obj instanceof Wiper)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Wiper ber_decode(InputStream in) throws IOException {
		return (Wiper)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65559);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Wiper.class);
		AsnConverter frontwiperwitchConverter = FRONTWIPERSWITCH.CONV;
		AsnConverter frontwiperintermswitchConverter = FRONTWIPERINTERMSWITCH.CONV;
		AsnConverter rearwiperConverter = REARWIPER.CONV;
		AsnConverter frontwashingswitchConverter = FRONTWASHINGSWITCH.CONV;
		CONV.setComponentConverters(new AsnConverter[] { frontwiperwitchConverter, frontwiperintermswitchConverter, rearwiperConverter, frontwashingswitchConverter });
	}


}