/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Resets & Calibrates the gyro when runned
 */
public class CalibrateGyro extends InstantCommand {

  public CalibrateGyro() {
    super();
    requires(Robot.drivetrain);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.drivetrain.getGyro().reset();
    Robot.drivetrain.getGyro().calibrate();
  }

}
