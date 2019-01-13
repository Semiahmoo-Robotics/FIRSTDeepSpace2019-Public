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

public class TankDrive extends Command {

  private double left;
  private double right;

  public TankDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    
    if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7){
      Robot.drivetrain.setBoostEngaged(true);

      Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());

    } else {
      Robot.drivetrain.setBoostEngaged(false);

      left = Robot.oi.GetXboxController().getY(Hand.kLeft) * RobotMap.MULTIPLYER;
      right = Robot.oi.GetXboxController().getY(Hand.kRight) * RobotMap.MULTIPLYER;
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
