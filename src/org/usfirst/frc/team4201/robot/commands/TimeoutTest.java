package org.usfirst.frc.team4201.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TimeoutTest extends Command {
	Timer stopwatch;
	int index;
	long wait;
	
	public TimeoutTest(int index, double timeout, long wait) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.index = index;
    	this.wait = wait;
    	setTimeout(timeout);
    	
    	stopwatch = new Timer();
    }
	
    public TimeoutTest(int index, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.index = index;
    	setTimeout(timeout);
    	
    	stopwatch = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stopwatch.reset();
    	stopwatch.start();
    	
    	try {
			wait(wait);							// Use Thread.sleep() as alternative(Sounds bad)?, otherwise stay with Timer?
		} catch (InterruptedException e) {
			
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	stopwatch.stop();
    	SmartDashboard.putNumber("Command(" + index + ") Duration", stopwatch.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	DriverStation.reportError("Error: Command was interrupted", false);
    	end();
    }
}
