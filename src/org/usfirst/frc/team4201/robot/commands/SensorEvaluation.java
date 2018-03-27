package org.usfirst.frc.team4201.robot.commands;

import org.usfirst.frc.team4201.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorEvaluation extends Command {
	Timer stopwatch;
	double lowerValue, upperValue, totalRange;
	
    public SensorEvaluation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	stopwatch = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lowerValue = Robot.testSubsystem.sensor.getAverageVoltage();
    	upperValue = Robot.testSubsystem.sensor.getAverageVoltage();
    	stopwatch.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double temp = Robot.testSubsystem.sensor.getAverageVoltage();
    	
    	if(temp < lowerValue)
    		lowerValue = temp;
    	if(temp > upperValue)
    		upperValue = temp;
    		
		totalRange = upperValue - lowerValue;
		SmartDashboard.putNumber("Lower Value", lowerValue);
		SmartDashboard.putNumber("Upper Value", upperValue);
		SmartDashboard.putNumber("Total Range", totalRange);
		SmartDashboard.putNumber("Runtime", stopwatch.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stopwatch.get() >= 180;
    }

    // Called once after isFinished returns true
    protected void end() {
    	stopwatch.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
