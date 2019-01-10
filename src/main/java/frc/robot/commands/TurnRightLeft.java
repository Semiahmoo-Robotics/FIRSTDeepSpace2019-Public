/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.OI;
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
    requires(Robot.driveTrain);
    wantAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialAngle = Robot.driveTrain.getGyro().getAngle();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
