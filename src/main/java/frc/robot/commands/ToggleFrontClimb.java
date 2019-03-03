/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleFrontClimb extends InstantCommand {
  public ToggleFrontClimb() {
    super();
    requires(Robot.climbPiston);
  }

  @Override
  protected void initialize() {
    if (Robot.climbPiston.frontExtended) {
      Robot.climbPiston.retractFront();
      Robot.climbPiston.frontExtended = false;
    } else {
      Robot.climbPiston.extendFront();
      Robot.climbPiston.frontExtended = true;
    }
  }

}
