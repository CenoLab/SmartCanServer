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

public class Light {

	@NotNull
	@Component(0)
	public TURNINGLAMPSWITCH turninglampswitch;

	@NotNull
	@Component(1)
	public EXTERIORLAMPSWITCH exexteriorlampswitch;

	@NotNull
	@Component(2)
	public FOGLAMPSWITCH foglampswitch;

	@NotNull
	@Component(3)
	public TELLTALEFRONTFOGLIGHT telltalefrontfoglight;

	@NotNull
	@Component(4)
	public TELLTALEREARFOGLIGHT telltalerearfoglight;

	@NotNull
	@Component(5)
	public LOWBEAMINDICATOR lowbeamingicator;

	@NotNull
	@Component(6)
	public HIGHBEAMINDICATOR highbeamindiator;

	@NotNull
	@Component(7)
	public BRAKELIGHTRIGHTACTIVE brakelightright;

	@NotNull
	@Component(8)
	public BRAKELIGHTLEFTACTIVE brakelightleftactive;

	@NotNull
	@Component(9)
	public PARKLIGHTONWARNING parklightonwarning;


	public boolean equals(Object obj) {
		if(!(obj instanceof Light)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Light ber_decode(InputStream in) throws IOException {
		return (Light)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65571);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Light.class);
		AsnConverter turninglampswitchConverter = TURNINGLAMPSWITCH.CONV;
		AsnConverter exexteriorlampswitchConverter = EXTERIORLAMPSWITCH.CONV;
		AsnConverter foglampswitchConverter = FOGLAMPSWITCH.CONV;
		AsnConverter telltalefrontfoglightConverter = TELLTALEFRONTFOGLIGHT.CONV;
		AsnConverter telltalerearfoglightConverter = TELLTALEREARFOGLIGHT.CONV;
		AsnConverter lowbeamingicatorConverter = LOWBEAMINDICATOR.CONV;
		AsnConverter highbeamindiatorConverter = HIGHBEAMINDICATOR.CONV;
		AsnConverter brakelightrightConverter = BRAKELIGHTRIGHTACTIVE.CONV;
		AsnConverter brakelightleftactiveConverter = BRAKELIGHTLEFTACTIVE.CONV;
		AsnConverter parklightonwarningConverter = PARKLIGHTONWARNING.CONV;
		CONV.setComponentConverters(new AsnConverter[] { turninglampswitchConverter, exexteriorlampswitchConverter, foglampswitchConverter, telltalefrontfoglightConverter, telltalerearfoglightConverter, lowbeamingicatorConverter, highbeamindiatorConverter, brakelightrightConverter, brakelightleftactiveConverter, parklightonwarningConverter });
	}


}