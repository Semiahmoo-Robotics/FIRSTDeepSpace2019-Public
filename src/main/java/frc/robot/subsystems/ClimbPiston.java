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
 * It uses two double solenoid connected to the pcm.
 */
public class ClimbPiston extends Subsystem {

  private final DoubleSolenoid m_lClimb;
  private final DoubleSolenoid m_rClimb;

  public ClimbPiston() {
    m_lClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.L_CLIMB_FWD, RobotMap.L_CLIMB_RVSE);
    m_rClimb = new DoubleSolenoid(RobotMap.PCM_MODULE, RobotMap.R_CLIMB_FWD, RobotMap.R_CLIMB_RVSE);
  }

  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }

  /**
   * Extend climb solenoid.
   * Right piston extends slower than Left piston.
   */
  public void extend() {
    m_rClimb.set(Value.kForward);
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    m_lClimb.set(Value.kForward);
  }

  public void extendRight() {
    m_rClimb.set(Value.kForward);
  }

  public void ExtendLeft() {
    m_lClimb.set(Value.kForward);
  }

  /**
   * retract climb solenoid.
   * Right piston retracts faster than left piston.
   */
  public void retract() {
    m_lClimb.set(Value.kReverse);
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    m_rClimb.set(Value.kReverse);

  }

  public void retractRight() {
    m_rClimb.set(Value.kReverse);
  }

  public void RetractLeft() {
    m_lClimb.set(Value.kReverse);
  }

  /**
   * turn off both climb solenoids.
   */
  public void off() {
    m_lClimb.set(Value.kOff);
    m_rClimb.set(Value.kOff);

  }


}
