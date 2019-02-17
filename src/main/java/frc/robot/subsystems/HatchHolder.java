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
public class HatchHolder extends Subsystem {

  private final Spark m_HatchHolder;

  public boolean HolderUp = false;

  public HatchHolder() {

    m_HatchHolder = new Spark(RobotMap.HATCHHOLDER);

  }

  public void SetHolder() {
    m_HatchHolder.set(0.6);
  } 

  public void ReverseSetHolder() {
    m_HatchHolder.set(-0.6);
  }

  public void StopHolder() {
    m_HatchHolder.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }


}
