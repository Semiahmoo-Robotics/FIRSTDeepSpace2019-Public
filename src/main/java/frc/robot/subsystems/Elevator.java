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
import frc.robot.commands.ChangeHeight;

/**
 * This is the backup elevator in which there is no encoder.
 */
public class Elevator extends Subsystem {

  private final Spark m_elevator;

  public Elevator() {
    m_elevator = new Spark(RobotMap.ELEVATOR);
  }

  /**
   * Sets the elevator's motor.
   * @param set PWM value to set the motor to
   */
  public void Set(double set) {
    m_elevator.set(set);
  } 

  /**
   * Stops the elevator.
   */
  public void StopHolder() {
    m_elevator.set(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChangeHeight());
  }
}
