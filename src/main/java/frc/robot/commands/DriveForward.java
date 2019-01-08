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
  //For gyro to correct direction without resetting the gyro
  private double initialHeading;

  /**
     * constant used for gyroscopic correction. If this value is too high, the robot
     * may oscillate.
     * 
     * This value should be tweaked through trial and error for best results.
     */
  private static final double GYRO_CORRECTION = 0.0275;

  public DriveForward(double distance, double speed, double timeout) {
    requires(Robot.drivetrain);

    if (speed > 1.0 || speed < -1.0) {
      throw new IllegalArgumentException("Speed value must be between 0 and 1");
    }

    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.drivetrain.getLEncoder().reset();
    Robot.drivetrain.getREncoder().reset();
    initialHeading = Robot.drivetrain.getGyro().getAngle();
    setTimeout(timeout);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    final double currentHeading = Robot.drivetrain.getGyro().getAngle();
    final double angleDifference = currentHeading - initialHeading;

    Robot.drivetrain.CurvatureDriveSet(speed, (GYRO_CORRECTION * angleDifference));
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

  private double distanceRemaining() {
    return distance - ((Robot.driveTrain.getLEncoder().getDistance() + Robot.driveTrain.getREncoder().getDistance()) / 2); // distance - average encoder distance
  }
}