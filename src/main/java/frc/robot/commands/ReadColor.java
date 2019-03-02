/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Reads the color sensor, and if the color detected is a white alignment line,
 * sends the rumble to notify the driver.
 */
public class ReadColor extends Command {

  public ReadColor() {
    requires(Robot.sensorAlign);
  }

  @Override
  protected void execute() {
    Robot.sensorAlign.read();
    if (Robot.sensorAlign.getRed() >= Robot.sensorAlign.WHITE_ERROR
        && Robot.sensorAlign.getBlue() >= Robot.sensorAlign.WHITE_ERROR
        && Robot.sensorAlign.getGreen() >= Robot.sensorAlign.WHITE_ERROR ) {

      Robot.oi.getXbox().setRumble(RumbleType.kLeftRumble, 1);
      Robot.oi.getXbox().setRumble(RumbleType.kRightRumble, 1);
    } else {
      Robot.oi.getXbox().setRumble(RumbleType.kLeftRumble, 0);
      Robot.oi.getXbox().setRumble(RumbleType.kRightRumble, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
