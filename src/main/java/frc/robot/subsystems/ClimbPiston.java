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
 * This subsystem controls the Climb pneumatics piston.
 * It uses three double solenoid connected to the pcm.
 * The solenoids control 3 big pistons and 2 small pistons.
 */
public class ClimbPiston extends Subsystem {

  private final DoubleSolenoid m_frontClimb;
  private final DoubleSolenoid m_backClimb;
  private final DoubleSolenoid m_smlClimb;

  public ClimbPiston() {
    m_frontClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.FRONT_CLIMB_FWD, RobotMap.FRONT_CLIMB_RVSE);
    m_backClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.BACK_CLIMB_FWD, RobotMap.BACK_CLIMB_RVSE);
    m_smlClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.SMALL_CLIMB_FWD, RobotMap.SMALL_CLIMB_RVSE);
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
    m_frontClimb.set(Value.kForward);
    m_backClimb.set(Value.kForward);
  }

  public void extendSmall() {
    m_smlClimb.set(Value.kForward);
  }

  /**
   * retract climb solenoid.
   */
  public void retract() {
    m_frontClimb.set(Value.kReverse);
    m_backClimb.set(Value.kReverse);

  }

  public void retractSmall() {
    m_smlClimb.set(Value.kReverse);
  }

  /**
   * turn off both climb solenoids.
   */
  public void off() {
    m_backClimb.set(Value.kOff);
    m_frontClimb.set(Value.kOff);

  }


}
