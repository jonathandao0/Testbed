package org.usfirst.frc.team4201.robot.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.usfirst.frc.team4201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorEvaluation extends Command {
	Timer stopwatch = new Timer();
	double lowerValue, upperValue, totalRange;
	FileWriter writer;
	BufferedWriter bufferedWriter;
	String filename = "temp";
	
    public SensorEvaluation(String filename) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
		this.filename = filename;
    }
    
    public SensorEvaluation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stopwatch.stop();
    	stopwatch.reset();
    	
    	// Initialize values
    	lowerValue = Robot.testSubsystem.sensor.getAverageVoltage();
    	upperValue = Robot.testSubsystem.sensor.getAverageVoltage();
    	stopwatch.start();
    	
    	try {
    		FileWriter writer = new FileWriter("/media/sda1/Pathfinder/" + filename + ".csv", true);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Time:,\tVoltage:");
		} catch(Exception e) {
			DriverStation.reportError("Error: Could not create file", false);
		}
    	/*	To Consider:
    	 * 	-Value drifting after robotInit()?
    	 * 	-Localized range/values? (avg. in a given period?)
    	 * 
    	 */
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double temp = Robot.testSubsystem.sensor.getAverageVoltage();
    	
    	try {
    		bufferedWriter.newLine();
            bufferedWriter.write(stopwatch.get() + ",\t" + temp);
		} catch(Exception e) {
			DriverStation.reportError("Error: Could not write new line", false);
		}
    	
    	if(temp < lowerValue)
    		lowerValue = temp;
    	if(temp > upperValue)
    		upperValue = temp;
    	if(upperValue - lowerValue > totalRange)
    		totalRange = upperValue - lowerValue;
		
    	SmartDashboard.putNumber("Lower Value", lowerValue);
		SmartDashboard.putNumber("Upper Value", upperValue);
		SmartDashboard.putNumber("Total Range", totalRange);
		SmartDashboard.putNumber("Runtime", stopwatch.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stopwatch.get() >= 180;	// Stop after 3 minutes
    }

    // Called once after isFinished returns true
    protected void end() {
    	stopwatch.stop();
    	try {
            bufferedWriter.close();
    	} catch(Exception e){
    		
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
