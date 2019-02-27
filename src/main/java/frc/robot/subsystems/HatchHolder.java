/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem controls one motor that acts like a servo.
 * By toggle, the hatch holder rotates to hold or release position.
 */
public class HatchHolder extends Subsystem {

  private final Spark m_HatchHolder;

  //Toggle to determine of the holder is at release or hold position.
  public boolean Hold = false;

  public HatchHolder() {

    m_HatchHolder = new Spark(RobotMap.HATCHHOLDER);

  }

  /**
   * Sets the hatch holder to release position.
   * @param set PWM value to set the motor to
   */
  public void Set(double set) {
    m_HatchHolder.set(set);
  } 

  /**
   * Reverses the hatch holder to Hold position.
   * @param set PWM value to set the motor to
   */
  public void Reverse(double set) {
    m_HatchHolder.set(-set);
  }

  /**
   * Stops the hatch holer in place.
   * Always call after using SetHolder() or ReverseSetHolder().
   */
  public void StopHolder() {
    m_HatchHolder.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }


}
