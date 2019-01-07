/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForward extends Command {

  private final double distance, speed, timeout;
  private static final double sc = 0.25;

  public DriveForward(double distance, double speed, double timeout) {
    requires(Robot.drivetrain);
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.getGyro().reset();
    Robot.drivetrain.getLEncoder().reset();
    Robot.drivetrain.getREncoder().reset();
    setTimeout(timeout);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double heading = Robot.drivetrain.getGyro().getAngle();
    Robot.drivetrain.CurvatureDriveSet(speed, -sc*heading);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.drivetrain.getREncoder().getDistance() >= distance || Robot.drivetrain.getLEncoder().getDistance() >= distance || isTimedOut()) {
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}