package org.usfirst.frc.team4201.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SchedulerTest extends Command {
	Timer stopwatch;
	public static int index;
	int i;
	long wait;
	double totalTime;
	
	public SchedulerTest(double timeout, long wait) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
		i = index++;
    	this.wait = wait;
    	setTimeout(timeout + wait);
    	SmartDashboard.putNumber("Total Time", timeout + wait);
    	stopwatch = new Timer();
    }
	
    public SchedulerTest(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	i = index++;
    	setTimeout(timeout);
    	
    	stopwatch = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stopwatch.reset();
    	stopwatch.start();
    	
    	try {
			Thread.sleep(wait * 1000);
		} catch (InterruptedException e) {
		}
    	
    	SmartDashboard.putString("Timout Status", "Initializing...");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Timout Status", "Running...");
    	SmartDashboard.putNumber("Command(" + i + ") Duration", stopwatch.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	stopwatch.stop();
    	SmartDashboard.putString("Timout Status", "Finished...");
    	SmartDashboard.putNumber("Command(" + i + ") Duration", stopwatch.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	DriverStation.reportError("Error: Command was interrupted", false);
    	end();
    }
}
