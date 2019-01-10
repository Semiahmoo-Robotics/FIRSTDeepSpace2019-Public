/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

public class TurnRightLeft extends Command {
  private double wantAngle;
  private double initialAngle;
  private double currentAngle;


  private boolean turnRight;
  private static final double GYRO_CONNECTION = 0.0275;


  public TurnRightLeft(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    wantAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialAngle = Robot.drivetrain.getGyro().getAngle();
    if(wantAngle > 0) {
      turnRight = true;
    } else turnRight = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(turnRight) {
      Robot.drivetrain.TankDriveSet(0.3, -0.3);
    } else Robot.drivetrain.TankDriveSet(-0.3, 0.3);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(currentAngle == wantAngle) {
      return true;
    }else return false;
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
