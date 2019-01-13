/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArcadeDrive extends Command {
  private static final double MULTIPLYER = 0.6;
  private double xForward;
  private double zRotation;
  
  public ArcadeDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    Robot.drivetrain.ArcadeDriveSet(Robot.oi.GetXboxController());

    if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kLeft) >= 0.7){
      Robot.drivetrain.boostEngaged = false;

      Robot.drivetrain.ArcadeDriveSet(Robot.oi.GetXboxController());

    } else {
      Robot.drivetrain.boostEngaged = true;

      xForward = Robot.oi.GetXboxController().getY() * MULTIPLYER;
      zRotation = Robot.oi.GetXboxController().getX() * MULTIPLYER;
      Robot.drivetrain.TankDriveSet(xForward, zRotation);
    }

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
