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

public class ArcadeOrTankDrive extends Command {
  
  //Tank Drive
  private double left;
  private double right;

  //Arcade Drive
  private double xForward;
  private double zRotation;
  
  public ArcadeOrTankDrive() {
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (Robot.drivetrain.getIsArcade()) {
      //Arcade Drive
      if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7){
        Robot.drivetrain.setBoostEngaged(true);
        Robot.drivetrain.ArcadeDriveSet(Robot.oi.GetXboxController());
      } else {
        Robot.drivetrain.setBoostEngaged(false);
        xForward = Robot.oi.GetXboxController().getY() * Robot.drivetrain.getBoostMultiplyer();
        zRotation = Robot.oi.GetXboxController().getX() * Robot.drivetrain.getBoostMultiplyer();
        Robot.drivetrain.ArcadeDriveSet(xForward, -zRotation);
      }

    } else {
      //Tank Drive
      if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7){
        Robot.drivetrain.setBoostEngaged(true);
        Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());
      } else {
        Robot.drivetrain.setBoostEngaged(false);
        left = Robot.oi.GetXboxController().getY(Hand.kLeft) * Robot.drivetrain.getBoostMultiplyer();
        right = Robot.oi.GetXboxController().getY(Hand.kRight) * Robot.drivetrain.getBoostMultiplyer();
        Robot.drivetrain.TankDriveSet(left, right);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

}
