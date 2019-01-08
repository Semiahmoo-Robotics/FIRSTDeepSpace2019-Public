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

  double distance, speed, sc, timeout;

  public DriveForward(double distance, double speed, double timeout) {
    requires(Robot.driveTrain);
    this.distance = distance;
    this.speed = speed;
    this.sc = 0.0275;
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.getGyro().reset();
    Robot.driveTrain.getLEncoder().reset();
    Robot.driveTrain.getREncoder().reset();
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
    if(distanceRemaining() <= 0 || isTimedOut()) {
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

  private double distanceRemaining() {
    return distance - ((Robot.driveTrain.getLEncoder().getDistance() + Robot.driveTrain.getREncoder().getDistance()) / 2); // distance - average encoder distance
  }
}