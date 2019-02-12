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

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    /*
    Remember, this method runs every 0.02 seconds!
    This means that the drivetrain's MaxSpeed, Drive Mode (Arcade or Tank), and
    Boost Multiplyer can change depending on the Network Table values real time, which 
    is accessed by the shuffle board.
    */

    //Set Max speed of the drivetrain from the value of the network table.
    Robot.drivetrain.GetNWTMaxspeed();

    if (Robot.drivetrain.GetNWTIsArcade()) {
      //Arcade Drive
      if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7) {
        //Boost Mode
        Robot.drivetrain.setBoostEngaged(true);
        Robot.drivetrain.ArcadeDriveSet(Robot.oi.GetXboxController());
      } else {
        //No Boost
        Robot.drivetrain.setBoostEngaged(false);
        xForward = Robot.oi.GetXboxController().getY() * Robot.drivetrain.getNWTBoostMultiplyer();
        zRotation = Robot.oi.GetXboxController().getX() * Robot.drivetrain.getNWTBoostMultiplyer();
        Robot.drivetrain.ArcadeDriveSet(xForward, -zRotation);
      }

    } else {
      //Tank Drive
      if (Robot.oi.GetXboxController().getTriggerAxis(Hand.kRight) >= 0.7){
        Robot.drivetrain.setBoostEngaged(true);
        Robot.drivetrain.TankDriveSet(Robot.oi.GetXboxController());
      } else {
        Robot.drivetrain.setBoostEngaged(false);
        left = Robot.oi.GetXboxController().getY(Hand.kLeft) * Robot.drivetrain.getNWTBoostMultiplyer();
        right = Robot.oi.GetXboxController().getY(Hand.kRight) * Robot.drivetrain.getNWTBoostMultiplyer();
        Robot.drivetrain.TankDriveSet(left, right);
      }
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
