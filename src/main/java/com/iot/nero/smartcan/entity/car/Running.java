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

public class Running {

	@NotNull
	@Component(0)
	public BreakPadelSwtichSts areakpadelswtich;

	@NotNull
	@Component(1)
	public Double battsoc;

	@NotNull
	@Component(2)
	public Double vcu616driverange;

	@NotNull
	@Component(3)
	public Double oilleft;

	@NotNull
	@Component(4)
	public Double longitudinalacceleration;

	@NotNull
	@Component(5)
	public Double longitudinaloffset;

	@NotNull
	@Component(6)
	public Double lateralacceleration;

	@NotNull
	@Component(7)
	public Double lateraloffset;

	@NotNull
	@Component(8)
	public Double yawvelocity;

	@NotNull
	@Component(9)
	public Double yawvelocityoffset;

	@NotNull
	@Component(10)
	public Double steeringwheelrotationspeed;

	@NotNull
	@Component(11)
	public Double steeringwheelangle;

	@NotNull
	@Component(12)
	public VALIDACCELERATOR validacceleator;

	@NotNull
	@Component(13)
	public Double acceleratorpos;

	@NotNull
	@Component(14)
	public Integer totaloso;

	@NotNull
	@Component(15)
	public VALIDWHEELSPEEDFL validwheelspeed;

	@NotNull
	@Component(16)
	public Double wheelspeedfl;

	@NotNull
	@Component(17)
	public VALIDWHEELSPEEDFR validwheelfr;

	@NotNull
	@Component(18)
	public Double wheelspeedfr;

	@NotNull
	@Component(19)
	public VALIDWHEELSPEEDRL validwheelspeedrl;

	@NotNull
	@Component(20)
	public Double wheelspeedrl;

	@NotNull
	@Component(21)
	public VALIDWHEELSPEEDRR validwheelrr;

	@NotNull
	@Component(22)
	public Double wheelspeedrr;

	@NotNull
	@Component(23)
	public VALIDVEHICLESPEED validvehiclespeed;

	@NotNull
	@Component(24)
	public Double vehiclespeed;

	@NotNull
	@Component(25)
	public Double motospeed;


	public boolean equals(Object obj) {
		if(!(obj instanceof Running)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Running ber_decode(InputStream in) throws IOException {
		return (Running)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65581);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Running.class);
		AsnConverter areakpadelswtichConverter = BreakPadelSwtichSts.CONV;
		AsnConverter battsocConverter = DoubleConverter.INSTANCE;
		AsnConverter vcu616driverangeConverter = DoubleConverter.INSTANCE;
		AsnConverter oilleftConverter = DoubleConverter.INSTANCE;
		AsnConverter longitudinalaccelerationConverter = DoubleConverter.INSTANCE;
		AsnConverter longitudinaloffsetConverter = DoubleConverter.INSTANCE;
		AsnConverter lateralaccelerationConverter = DoubleConverter.INSTANCE;
		AsnConverter lateraloffsetConverter = DoubleConverter.INSTANCE;
		AsnConverter yawvelocityConverter = DoubleConverter.INSTANCE;
		AsnConverter yawvelocityoffsetConverter = DoubleConverter.INSTANCE;
		AsnConverter steeringwheelrotationspeedConverter = DoubleConverter.INSTANCE;
		AsnConverter steeringwheelangleConverter = DoubleConverter.INSTANCE;
		AsnConverter validacceleatorConverter = VALIDACCELERATOR.CONV;
		AsnConverter acceleratorposConverter = DoubleConverter.INSTANCE;
		AsnConverter totalosoConverter = TotalODO.CONV;
		AsnConverter validwheelspeedConverter = VALIDWHEELSPEEDFL.CONV;
		AsnConverter wheelspeedflConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelfrConverter = VALIDWHEELSPEEDFR.CONV;
		AsnConverter wheelspeedfrConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelspeedrlConverter = VALIDWHEELSPEEDRL.CONV;
		AsnConverter wheelspeedrlConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelrrConverter = VALIDWHEELSPEEDRR.CONV;
		AsnConverter wheelspeedrrConverter = DoubleConverter.INSTANCE;
		AsnConverter validvehiclespeedConverter = VALIDVEHICLESPEED.CONV;
		AsnConverter vehiclespeedConverter = DoubleConverter.INSTANCE;
		AsnConverter motospeedConverter = DoubleConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { areakpadelswtichConverter, battsocConverter, vcu616driverangeConverter, oilleftConverter, longitudinalaccelerationConverter, longitudinaloffsetConverter, lateralaccelerationConverter, lateraloffsetConverter, yawvelocityConverter, yawvelocityoffsetConverter, steeringwheelrotationspeedConverter, steeringwheelangleConverter, validacceleatorConverter, acceleratorposConverter, totalosoConverter, validwheelspeedConverter, wheelspeedflConverter, validwheelfrConverter, wheelspeedfrConverter, validwheelspeedrlConverter, wheelspeedrlConverter, validwheelrrConverter, wheelspeedrrConverter, validvehiclespeedConverter, vehiclespeedConverter, motospeedConverter });
	}


}