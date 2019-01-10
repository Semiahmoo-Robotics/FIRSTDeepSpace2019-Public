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

public class TankDrive extends Command {

  //when boost is Engaged, max output is occured. otherwise, the multiplyer takes effect
  private boolean precisionEngaged = false;
  private static final double MULTIPLYER = 0.6;
  private double left;
  private double right;

  public TankDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    
    if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kLeft) >= 0.7){
      precisionEngaged = true;

      left = Robot.oi.GetXboxController().getY(Hand.kLeft) * MULTIPLYER;
      right = Robot.oi.GetXboxController().getY(Hand.kRight) * MULTIPLYER;
      Robot.drivetrain.TankDriveSet(left, right);

    } else {
      precisionEngaged = false;

      Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());
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
