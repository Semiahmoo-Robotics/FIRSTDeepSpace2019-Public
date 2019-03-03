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
  private final DoubleSolenoid m_sideClimb;

  public boolean frontExtended, backExtended, smallExtended, sideExtended = false;

  public ClimbPiston() {
    m_frontClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.FRONT_CLIMB_FWD, RobotMap.FRONT_CLIMB_RVSE);
    m_backClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.BACK_CLIMB_FWD, RobotMap.BACK_CLIMB_RVSE);
    m_smlClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.SMALL_CLIMB_FWD, RobotMap.SMALL_CLIMB_RVSE);
    m_sideClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.SIDE_CLIMB_FWD, RobotMap.SIDE_CLIMB_RVSE);
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
   * Extend small solenoid.
   */  
  public void extendSmall() {
    m_smlClimb.set(Value.kForward);
    smallExtended = true;
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
   * Retract small solenoid.
   */  
  public void retractSmall() {
    m_smlClimb.set(Value.kReverse);
    smallExtended = false;
  }

    /**
   * Extend side solenoid.
   */  
  public void ExtendSide() {
    m_sideClimb.set(Value.kForward);
    sideExtended = true;
  }

  /**
   * Retract side solenoid.
   */  
  public void retractSide() {
    m_sideClimb.set(Value.kReverse);
    sideExtended = false;
  }

}
