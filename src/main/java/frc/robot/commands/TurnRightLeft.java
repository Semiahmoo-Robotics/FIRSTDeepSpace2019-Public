/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnRightLeft extends Command {
  private final double difAngle;
  private double initialAngle;
  private double currentAngle;
  private double finalAngle;

  private double speed;

  private boolean turnRight;
  private static final double GYRO_CONNECTION = 0.0275;


  public TurnRightLeft(double angle, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    difAngle = angle;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialAngle = Robot.drivetrain.getGyro().getAngle();
    finalAngle = difAngle + initialAngle;
    if(difAngle > 0) {
      turnRight = true;
    } else turnRight = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    currentAngle = Robot.drivetrain.getGyro().getAngle();

    if(turnRight) {
      Robot.drivetrain.TankDriveSet(speed, -speed);
    } else Robot.drivetrain.TankDriveSet(-speed, speed);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    boolean finished = false;

    if(turnRight) {
      if(currentAngle >= finalAngle ) {
        finished = true;
      }else finished = false;
    }
    if(!turnRight) {
      if(currentAngle <= finalAngle) {
        finished = true;
      }else finished = false;
    }
    return finished;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
