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

public class ArcadeDrive extends Command {
  private double xForward;
  private double zRotation;
  
  public ArcadeDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {

    if (Robot.oi.getP1Xbox().getTriggerAxis(Hand.kLeft) >= 0.7) {
      Robot.drivetrain.setBoostEngaged(true);
      Robot.drivetrain.ArcadeDriveSet(Robot.oi.getP1Xbox());

    } else if (Robot.oi.getP1Xbox().getTriggerAxis(Hand.kRight) >= 0.7) {
      Robot.drivetrain.setPrecisionEngaged(true);
      xForward = Robot.oi.getP1Xbox().getY(Hand.kLeft) * Robot.drivetrain.PRECISION_MULTIPLIER;
      zRotation = Robot.oi.getP1Xbox().getX(Hand.kLeft) * Robot.drivetrain.PRECISION_MULTIPLIER;
      Robot.drivetrain.TankDriveSet(xForward, -zRotation);

    } else {
      Robot.drivetrain.setBoostEngaged(false);

      xForward = Robot.oi.getP1Xbox().getY(Hand.kLeft) * Robot.drivetrain.NORMAL_MULTIPLIER;
      zRotation = Robot.oi.getP1Xbox().getX(Hand.kLeft) * Robot.drivetrain.NORMAL_MULTIPLIER;
      Robot.drivetrain.ArcadeDriveSet(xForward, -zRotation);
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
