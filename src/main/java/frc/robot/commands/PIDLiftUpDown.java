/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PIDLiftUpDown extends Command {

  public PIDLiftUpDown() {
    requires(Robot.forklift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.oi.GetXboxController().getPOV() == 0) {
      Robot.forklift.setSetpointRelative(0.5);
    } else if (Robot.oi.GetXboxController().getPOV() == 180) {
      Robot.forklift.setSetpointRelative(-0.5);
    }
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
