/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4201.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4201.robot.Robot;
import org.usfirst.frc.team4201.robot.subsystems.TestSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class LimitSwitchTest extends Command {
	public LimitSwitchTest() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.testSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double yAxis = -Robot.oi.testController.getRawAxis(1);
		
		if(Math.abs(yAxis) > 0.05) {
			if(yAxis > 0)
				TestSubsystem.counter++;
			else
				if(!Robot.testSubsystem.limitSwitchTest.get())	// How can this fail?
					TestSubsystem.counter--;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
