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
import frc.robot.utils.DashboardKeys;

public class TankDrive extends Command {

  //when boost is Engaged, max output is occured. otherwise, the multiplyer takes effect
  private boolean boostEngaged = false;
  private static final double MULTIPLYER = 0.6;
  private double left;
  private double right;

  public TankDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {

    Robot.drivetrain.getLEncoder().reset();
    Robot.drivetrain.getREncoder().reset();
    SmartDashboard.putNumber(DashboardKeys.INIT_HEADING, Robot.drivetrain.getGyro().getAngle());
  }

  @Override
  protected void execute() {
    
    if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kLeft) >= 0.7){
      boostEngaged = false;

      Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());

    } else {
      boostEngaged = true;

      left = Robot.oi.GetXboxController().getY(Hand.kLeft) * MULTIPLYER;
      right = Robot.oi.GetXboxController().getY(Hand.kRight) * MULTIPLYER;
      Robot.drivetrain.TankDriveSet(left, right);
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
