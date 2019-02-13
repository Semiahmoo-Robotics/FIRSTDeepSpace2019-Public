/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Robot;

public class ClawHeightSet extends Command {

  private double targetAngle;
  private double currentAngle;
  private double timeout;
  private NetworkTableEntry secondaryMultiplier;
  

  public ClawHeightSet(double angle, double timeout) {
    requires(Robot.clawArm);
    if(angle < 0 || angle > 180) {
      throw new IllegalArgumentException("Value must be between 0.0 and 180.0");
    }

    this.targetAngle = angle;
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(timeout);
    currentAngle = Robot.clawArm.m_pEncoder.getDistance();

    if(targetAngle == currentAngle) {
      throw new IllegalArgumentException("Angle does not need to be changed!");
    }
    secondaryMultiplier = Shuffleboard.getTab("Test").add("Multiplier", -0.3).getEntry();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed;

    if(currentAngle < targetAngle) {
      speed = 0.5;
    } else {
      speed = -0.5;
    }

    boolean targetReached = false;
    while(targetReached == false) {
      Robot.clawArm.primaryMove(speed);
      Robot.clawArm.secondaryMove(speed*(secondaryMultiplier.getDouble(-0.3)));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(targetAngle == currentAngle || isTimedOut()){
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.clawArm.stop();
  }
}
