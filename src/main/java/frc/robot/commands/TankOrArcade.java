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

public class TankOrArcade extends Command {

  private double left;
  private double right;

  private double xForward;
  private double zRotation;

  public TankOrArcade() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    if (Robot.drivetrain.arcadeEnabled) {
      arcadeDrive();
    } else {
      tankDrive();
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

  @Override
  protected void interrupted() {
    end();
  }

  protected void tankDrive() {

    if (Robot.oi.getP1Xbox().getTriggerAxis(Hand.kLeft) >= 0.7) {
      Robot.drivetrain.setBoostEngaged(true);
      Robot.drivetrain.TankDriveSet(Robot.oi.getP1Xbox());

    } else if (Robot.oi.getP1Xbox().getTriggerAxis(Hand.kRight) >=0.7) {
      Robot.drivetrain.setPrecisionEngaged(true);
      left = Robot.oi.getP1Xbox().getY(Hand.kLeft) * Robot.drivetrain.PRECISION_MULTIPLIER;
      right = Robot.oi.getP1Xbox().getY(Hand.kRight) * Robot.drivetrain.PRECISION_MULTIPLIER;
      Robot.drivetrain.TankDriveSet(left, right);

    } else {
      Robot.drivetrain.setBoostEngaged(false);
      Robot.drivetrain.setPrecisionEngaged(false);
      left = Robot.oi.getP1Xbox().getY(Hand.kLeft) * Robot.drivetrain.NORMAL_MULTIPLIER;
      right = Robot.oi.getP1Xbox().getY(Hand.kRight) * Robot.drivetrain.NORMAL_MULTIPLIER;
      Robot.drivetrain.TankDriveSet(left, right);

    }
  }

  protected void arcadeDrive() {

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

}
