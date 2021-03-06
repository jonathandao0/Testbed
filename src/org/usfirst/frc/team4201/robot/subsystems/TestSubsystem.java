/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4201.robot.subsystems;

import org.usfirst.frc.team4201.robot.commands.LimitSwitchTest;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TestSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static int counter = 0;
	
	public DigitalInput limitSwitchTest = new DigitalInput(0);
	public AnalogInput sensor = new AnalogInput(0);
	public AnalogPotentiometer pot = new AnalogPotentiometer(sensor, 1080, 0);
	
	public void updateSmartDashboard() {
		SmartDashboard.putBoolean("LimitSwitchValue", limitSwitchTest.get());
		SmartDashboard.putNumber("Counter", counter);
		SmartDashboard.putNumber("Potentiometer", pot.get());
		SmartDashboard.putNumber("Potentiometer Voltage", sensor.getAverageVoltage());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LimitSwitchTest());
	}
}
