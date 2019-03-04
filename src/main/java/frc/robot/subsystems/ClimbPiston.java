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
 * m_frontClimb controls middle 18in piston
 * m_backClimb controls 2 back 18in piston
 * m_mediumClimb controls 2 side 10in piston
 * m_smlClimb controls 2 small pistons
 */
public class ClimbPiston extends Subsystem {

  private final DoubleSolenoid m_frontClimb;
  private final DoubleSolenoid m_backClimb;
  private final DoubleSolenoid m_mediumClimb;
  private final DoubleSolenoid m_smlClimb;

  public boolean frontExtended, backExtended, mediumExtended, smlExtended = false;

  public ClimbPiston() {
    m_frontClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.FRONT_CLIMB_FWD, RobotMap.FRONT_CLIMB_RVSE);
    m_backClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.BACK_CLIMB_FWD, RobotMap.BACK_CLIMB_RVSE);
    m_mediumClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.MEDIUM_CLIMB_FWD, RobotMap.MEDIUM_CLIMB_RVSE);
    m_smlClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.SMALL_CLIMB_FWD, RobotMap.SMALL_CLIMB_RVSE);
  }

  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }

  /**
   * Extend front solenoid.
   */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
  public void extendFront() {
    m_frontClimb.set(Value.kForward);
    frontExtended = true;
  }

  /**
   * Extend back solenoid.
   */  
  public void extendBack() {
    m_backClimb.set(Value.kForward);
    backExtended = true;
  }

  /**
   * Extend medium solenoid.
   */  
  public void extendMedium() {
    m_mediumClimb.set(Value.kForward);
    mediumExtended = true;
  }

  /**
   * Extend side solenoid.
   */  
  public void ExtendSmall() {
    m_smlClimb.set(Value.kForward);
    smlExtended = true;
  }

  /**
   * Retract front solenoid.
   */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
  public void retractFront() {
    m_frontClimb.set(Value.kReverse);
    frontExtended = false;
  }

  /**
   * Extend back solenoid.
   */  
  public void retractBack() {
    m_backClimb.set(Value.kReverse);
    backExtended = false;
  }

  /**
   * Retract medium solenoid.
   */  
  public void retractMedium() {
    m_mediumClimb.set(Value.kReverse);
    mediumExtended = false;
  }

  /**
   * Retract small solenoid.
   */  
  public void retractSmall() {
    m_smlClimb.set(Value.kReverse);
    smlExtended = false;
  }

}
