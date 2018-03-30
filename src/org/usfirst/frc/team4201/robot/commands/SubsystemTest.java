package org.usfirst.frc.team4201.robot.commands;

import org.usfirst.frc.team4201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SubsystemTest extends Command {
	Timer stopwatch;
	public static int index;
	int i;
	long wait;
	double totalTime;
	
	public SubsystemTest(){
		requires(Robot.testSubsystem);
		
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	SmartDashboard.putString("Default Command", "Initializing...");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Default Command", "Running...");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Default Command", "Ending...");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
