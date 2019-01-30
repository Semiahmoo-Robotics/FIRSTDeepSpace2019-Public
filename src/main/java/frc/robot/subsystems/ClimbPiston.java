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

  private final DoubleSolenoid m_LclimbPiston;
  private final DoubleSolenoid m_RclimbPiston;


  public ClimbPiston() {
    m_LclimbPiston = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.LCLIMB_FORWARD_CHN, RobotMap.LCLIMB_REVERSE_CHN);
    m_RclimbPiston = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.RCLIMB_FORWARD_CHN, RobotMap.RCLIMB_REVERSE_CHN);
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
    m_LclimbPiston.set(Value.kForward);
    m_RclimbPiston.set(Value.kForward);

  }

  /**
   * retract climb solenoid.
   */
  public void retract() {
    m_LclimbPiston.set(Value.kReverse);
    m_RclimbPiston.set(Value.kForward);

  }

  /**
   * turn off climb solenoid.
   */
  public void off() {
    m_LclimbPiston.set(Value.kOff);
    m_RclimbPiston.set(Value.kForward);

  }


}
