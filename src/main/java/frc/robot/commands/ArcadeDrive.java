/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.utils.DashboardKeys;

public class ArcadeDrive extends Command {
  private double xForward;
  private double zRotation;
  
  public ArcadeDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putNumber(DashboardKeys.INIT_HEADING, Robot.drivetrain.getGyroAngle());
  }

  @Override
  protected void execute() {

    if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kLeft) >= 0.7) {
      Robot.drivetrain.setBoostEngaged(true);
      Robot.drivetrain.ArcadeDriveSet(Robot.oi.GetXboxController());

    } else if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7) {
      Robot.drivetrain.setPrecisionEngaged(true);
      xForward = Robot.oi.GetXboxController().getY(Hand.kLeft) * Robot.drivetrain.PRECISION_MULTIPLIER;
      zRotation = Robot.oi.GetXboxController().getX(Hand.kLeft) * Robot.drivetrain.PRECISION_MULTIPLIER;
      Robot.drivetrain.TankDriveSet(xForward, -zRotation);

    } else {
      Robot.drivetrain.setBoostEngaged(false);

      xForward = Robot.oi.GetXboxController().getY(Hand.kLeft) * Robot.drivetrain.NORMAL_MULTIPLIER;
      zRotation = Robot.oi.GetXboxController().getX(Hand.kLeft) * Robot.drivetrain.NORMAL_MULTIPLIER;
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
