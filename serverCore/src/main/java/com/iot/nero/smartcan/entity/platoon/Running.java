/*
 * Generated by ASN.1 Java Compiler (https://www.asnlab.org/)
 * From ASN.1 module "Platoon"
 */
package com.iot.nero.smartcan.entity.platoon;

import java.io.*;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.*;
import org.asnlab.asndt.runtime.type.AsnType;

public class Running {

	@Component(0)
	public BreakPadelSwtichSts breakpadelswtich;

	@Component(1)
	public Double breakCircuitPressure;

	@Component(2)
	public Double battsoc;

	@Component(3)
	public Double vcu616driverange;

	@Component(4)
	public Double oilleft;

	@Component(5)
	public Double longitudinalacceleration;

	@Component(6)
	public Double lateralacceleration;

	@Component(7)
	public Double higheracceleration;

	@Component(8)
	public Double yawvelocity;

	@Component(9)
	public Double steeringwheelrotationspeed;

	@Component(10)
	public Double steeringwheelangle;

	@Component(11)
	public Double sassteerwheelrotspd;

	@Component(12)
	public Double steerwheelcurpos;

	@Component(13)
	public Double acceleratorpos;

	@Component(14)
	public Double throttlePercentage;

	@Component(15)
	public Integer totaloso;

	@Component(16)
	public VALIDWHEELSPEEDFL validwheelspeed;

	@Component(17)
	public Double wheelspeedfl;

	@Component(18)
	public VALIDWHEELSPEEDFR validwheelfr;

	@Component(19)
	public Double wheelspeedfr;

	@Component(20)
	public VALIDWHEELSPEEDRL validwheelspeedrl;

	@Component(21)
	public Double wheelspeedrl;

	@Component(22)
	public VALIDWHEELSPEEDRR validwheelrr;

	@Component(23)
	public Double wheelspeedrr;

	@Component(24)
	public Double vehiclespeed;

	@Component(25)
	public Double motospeed;

	@Component(26)
	public Double motoTorque;

	@Component(27)
	public Double accaebaebstate;

	@Component(28)
	public Double rotation;

	@Component(29)
	public Double pitchangle;

	@Component(30)
	public Double rollangle;

	@Component(31)
	public Double azimuthangle;


	public boolean equals(Object obj) {
		if(!(obj instanceof Running)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void print(PrintStream out) {
		TYPE.print(this, CONV, out);
	}

	public void ber_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.BASIC_ENCODING_RULES, CONV, out);
	}

	public static Running ber_decode(InputStream in) throws IOException {
		return (Running)TYPE.decode(in, EncodingRules.BASIC_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = Platoon.type(65569);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Running.class);
		AsnConverter breakpadelswtichConverter = BreakPadelSwtichSts.CONV;
		AsnConverter breakCircuitPressureConverter = DoubleConverter.INSTANCE;
		AsnConverter battsocConverter = DoubleConverter.INSTANCE;
		AsnConverter vcu616driverangeConverter = DoubleConverter.INSTANCE;
		AsnConverter oilleftConverter = DoubleConverter.INSTANCE;
		AsnConverter longitudinalaccelerationConverter = DoubleConverter.INSTANCE;
		AsnConverter lateralaccelerationConverter = DoubleConverter.INSTANCE;
		AsnConverter higheraccelerationConverter = DoubleConverter.INSTANCE;
		AsnConverter yawvelocityConverter = DoubleConverter.INSTANCE;
		AsnConverter steeringwheelrotationspeedConverter = DoubleConverter.INSTANCE;
		AsnConverter steeringwheelangleConverter = DoubleConverter.INSTANCE;
		AsnConverter sassteerwheelrotspdConverter = DoubleConverter.INSTANCE;
		AsnConverter steerwheelcurposConverter = DoubleConverter.INSTANCE;
		AsnConverter acceleratorposConverter = DoubleConverter.INSTANCE;
		AsnConverter throttlePercentageConverter = DoubleConverter.INSTANCE;
		AsnConverter totalosoConverter = IntegerConverter.INSTANCE;
		AsnConverter validwheelspeedConverter = VALIDWHEELSPEEDFL.CONV;
		AsnConverter wheelspeedflConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelfrConverter = VALIDWHEELSPEEDFR.CONV;
		AsnConverter wheelspeedfrConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelspeedrlConverter = VALIDWHEELSPEEDRL.CONV;
		AsnConverter wheelspeedrlConverter = DoubleConverter.INSTANCE;
		AsnConverter validwheelrrConverter = VALIDWHEELSPEEDRR.CONV;
		AsnConverter wheelspeedrrConverter = DoubleConverter.INSTANCE;
		AsnConverter vehiclespeedConverter = DoubleConverter.INSTANCE;
		AsnConverter motospeedConverter = DoubleConverter.INSTANCE;
		AsnConverter motoTorqueConverter = DoubleConverter.INSTANCE;
		AsnConverter accaebaebstateConverter = DoubleConverter.INSTANCE;
		AsnConverter rotationConverter = DoubleConverter.INSTANCE;
		AsnConverter pitchangleConverter = DoubleConverter.INSTANCE;
		AsnConverter rollangleConverter = DoubleConverter.INSTANCE;
		AsnConverter azimuthangleConverter = DoubleConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { breakpadelswtichConverter, breakCircuitPressureConverter, battsocConverter, vcu616driverangeConverter, oilleftConverter, longitudinalaccelerationConverter, lateralaccelerationConverter, higheraccelerationConverter, yawvelocityConverter, steeringwheelrotationspeedConverter, steeringwheelangleConverter, sassteerwheelrotspdConverter, steerwheelcurposConverter, acceleratorposConverter, throttlePercentageConverter, totalosoConverter, validwheelspeedConverter, wheelspeedflConverter, validwheelfrConverter, wheelspeedfrConverter, validwheelspeedrlConverter, wheelspeedrlConverter, validwheelrrConverter, wheelspeedrrConverter, vehiclespeedConverter, motospeedConverter, motoTorqueConverter, accaebaebstateConverter, rotationConverter, pitchangleConverter, rollangleConverter, azimuthangleConverter });
	}


}
