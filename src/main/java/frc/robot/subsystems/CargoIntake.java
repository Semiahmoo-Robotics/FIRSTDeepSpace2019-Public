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
 * This subsystem controls the intake motors for the cargo intake.
 * Connected to a SPARK motor controller.
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

  public void SetIntake(double set) {
    m_lIntake.set(set);
    m_rIntake.set(set);
  } 

  public void ReverseSetIntake(double set) {
    m_lIntake.set(set);
    m_rIntake.set(set);
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
