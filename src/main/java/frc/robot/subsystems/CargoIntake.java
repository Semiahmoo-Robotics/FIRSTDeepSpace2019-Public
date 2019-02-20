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
public class CargoIntake extends Subsystem {

  private final Spark m_lIntake;
  private final Spark m_rIntake;

  public CargoIntake() {

    m_lIntake = new Spark(RobotMap.L_INTAKE);
    m_lIntake.setInverted(true);
    m_rIntake = new Spark(RobotMap.R_INTAKE);
    m_rIntake.setInverted(true);

  }

  public void SetIntake() {
    m_lIntake.set(0.3);
    m_rIntake.set(0.3);
  } 

  public void SetStrongIntake() {
    m_lIntake.set(1.0);
    m_rIntake.set(1.0);
  } 

  public void ReverseSetIntake() {
    m_lIntake.set(-0.3);
    m_rIntake.set(-0.3);
  }

  public void ReverseStrongSetIntake() {
    m_lIntake.set(-1.0);
    m_rIntake.set(-1.0);
  }

  public void StopIntake() {
    m_lIntake.set(0);
    m_rIntake.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }


}
