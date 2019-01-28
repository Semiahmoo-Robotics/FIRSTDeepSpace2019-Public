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
public class ClimbPiston extends Subsystem {

  private final DoubleSolenoid m_climbPiston;


  public ClimbPiston() {
    m_climbPiston = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.CLIMB_FORWARD_CHN, RobotMap.CLIMB_REVERSE_CHN);
  }

  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }

  /**
   * Extend climb solenoid.
   */
  public void extend() {
    m_climbPiston.set(Value.kForward);
  }

  /**
   * retract climb solenoid.
   */
  public void retract() {
    m_climbPiston.set(Value.kReverse);
  }

  /**
   * turn off climb solenoid.
   */
  public void off() {
    m_climbPiston.set(Value.kOff);
  }


}
