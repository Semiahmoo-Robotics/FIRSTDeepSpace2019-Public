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
 * The intake motors for the previous APOLO robot in 2018 FIRST POWER UP Season.
 * This subsystem is only for the test robot.
 */
public class Intake extends Subsystem {

  private final Spark m_LeftIntake;
  private final Spark m_RightIntake;

  public Intake() {

    m_LeftIntake = new Spark(RobotMap.LEFT_INTAKE_PORT);
    m_RightIntake = new Spark(RobotMap.RIGHT_INTAKE_PORT);
    m_RightIntake.setInverted(true);

  }

  public void SetIntake() {
    m_LeftIntake.set(0.5);
    m_RightIntake.set(0.5);
  } 

  public void ReverseSetIntake() {
    m_LeftIntake.set(-0.5);
    m_RightIntake.set(-0.5);

  }

  public void StopIntake() {
    m_LeftIntake.set(0);
    m_RightIntake.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }


}
