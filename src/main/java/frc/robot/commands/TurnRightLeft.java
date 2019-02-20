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
  
  /**
   * The difference of angle between the initial angle and the final angle.
   * It is the desired angle of the robot.
   */
  private final double difAngle;

  /**
   * The initial angle the robot was before it starts to rotate.
   */
  private double initialAngle;

  /**
   * The current angle of the robot while the robot rotates.
   * It is updated periodically in the execute() method.
   */
  private double currentAngle;

  /**
   * initialAngle + difAngle.
   * The angle the robot will be at when the command is finished.
   */
  private double finalAngle;

  /**
   * The speed in which the robot will turn at
   */
  private double speed;

  /**
   * choose if the robot will turn right or left, depending on the difAngle variable's sign.
   */
  private boolean turnRight;

  public TurnRightLeft(double difAngle, double speed) {

    requires(Robot.drivetrain);
    this.difAngle = difAngle;
    this.speed = speed;
  }

  // Called just before this Command runs the first time.
  @Override
  protected void initialize() {

    initialAngle = Robot.drivetrain.getGyroAngle();
    finalAngle = difAngle + initialAngle;

    if(difAngle > 0) turnRight = true;
    else turnRight = false;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    currentAngle = Robot.drivetrain.getGyroAngle();

    if(turnRight) {
      Robot.drivetrain.TankDriveSet(speed, -speed);
    } else Robot.drivetrain.TankDriveSet(-speed, speed);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    boolean finished = false;

    if(turnRight) {
      if(currentAngle >= finalAngle) {
        finished = true;
      } else finished = false;
    }
    if(!turnRight) {
      if(currentAngle <= finalAngle) {
        finished = true;
      } else finished = false;
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
