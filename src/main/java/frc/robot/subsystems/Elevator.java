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
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  private final Spark m_elevator;

  public Elevator() {

    m_elevator = new Spark(RobotMap.ELEVATOR);

  }

  public void Set(double set) {
    m_elevator.set(set);
  } 

  public void Stop() {
    m_elevator.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }
}
