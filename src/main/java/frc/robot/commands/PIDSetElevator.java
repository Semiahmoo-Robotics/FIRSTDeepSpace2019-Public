/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PIDSetElevator extends Command {

  private double setpoint;

  public PIDSetElevator(double setpoint) {
    requires(Robot.elevator);
    this.setpoint = setpoint;
  }

  @Override
  protected void initialize() {
    Robot.elevator.ChangeSetPoint(setpoint);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.DisablePID();
  }

  @Override
  protected void interrupted() {
    this.end();
  }
}
