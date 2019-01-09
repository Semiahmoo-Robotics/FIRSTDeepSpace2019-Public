/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TankDrive extends Command {
  public TankDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {

    Robot.drivetrain.getLEncoder().reset();
    Robot.drivetrain.getREncoder().reset();
    SmartDashboard.putNumber("Initial Gyro Heading", Robot.drivetrain.getGyro().getAngle());
  }

  @Override
  protected void execute() {
    Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}
