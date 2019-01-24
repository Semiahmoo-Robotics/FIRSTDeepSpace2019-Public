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
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.utils.DashboardKeys;
import frc.robot.utils.Utils;
import frc.robot.subsystems.Drivetrain;

public class MecanumDrive extends Command {

  private double left;
  private double forward;
  private XboxController controller = Robot.oi.GetXboxController();
  private Drivetrain drivetrain = Robot.drivetrain;

  public MecanumDrive() {
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
    
    if (controller.getBButtonPressed()){ //B button
      drivetrain.setBoostEngaged(true); //Boost on
    } else {
      Robot.drivetrain.setBoostEngaged(false); //Boost off
    }

    if(controller.getTriggerAxis(Hand.kLeft) >= 0.7) {//Left trigger
      drivetrain.TankDriveSet(-1.0, 1.0); //Turn left
    }else if(controller.getTriggerAxis(Hand.kRight) >= 0.7) {//Right trigger
      drivetrain.TankDriveSet(1.0, -1.0); //Turn right
    }else { //No trigger
      forward = controller.getY(Hand.kLeft);
      drivetrain.TankDriveSet(forward, forward); // Drive forward on left joystick's Y ammount
    }
    left = controller.getX(Hand.kLeft);
    Robot.drivetrain.MecanumDriveSet(left); //Drive sideways based on left joystick's X amount

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