/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoClaw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 
  DoubleSolenoid piston1;

  public CargoClaw() {
    piston1 = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.PISTON1_FORWARD_CHN, RobotMap.PISTON1_REVERSE_CHN);
  }


  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }

  /**
   * Extend solenoid.
   */
  public void extend() {
    piston1.set(Value.kForward);
  }

  public void retract() {
    piston1.set(Value.kReverse);
  }

  public void off() {
    piston1.set(Value.kOff);
  }

}
