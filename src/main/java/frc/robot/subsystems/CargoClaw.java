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
 * This subsystem controls the Cargo Claw pneumatics piston.
 * It uses one double solenoid connected to the pcm.
 * Not used in final Robot Product
 */
public class CargoClaw extends Subsystem {
 
  private final DoubleSolenoid m_claw;

  public boolean extended = false;

  public CargoClaw() {
    m_claw = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.CLAW_FORWARD_CHN, RobotMap.CLAW_REVERSE_CHN);
  }


  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }

  /**
   * Extend claw solenoid.
   */
  public void extend() {
    m_claw.set(Value.kForward);
  }

  /**
   * Retract claw solenoid.
   */
  public void retract() {
    m_claw.set(Value.kReverse);
  }

  /**
   * turn off claw solenoid.
   */
  public void off() {
    m_claw.set(Value.kOff);
  }

}
