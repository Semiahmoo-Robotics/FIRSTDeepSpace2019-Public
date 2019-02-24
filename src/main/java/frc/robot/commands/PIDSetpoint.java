/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PIDSetpoint extends Command {

  private double set;

  public PIDSetpoint(double set) {
    requires(Robot.forklift);
    this.set = set;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.forklift.setSetpoint(set);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
  
}
