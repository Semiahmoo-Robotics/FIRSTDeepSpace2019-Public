/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This subsystem controls the pneumatics compressor wired to the pcm.
 * This subsystem is seperate from the other pneumatics subsystems,
 * such as the CargoClaw and the ClimbPiston.
 */
public class Pneumatics extends Subsystem {

  private final Compressor m_compressor;
  
  public Pneumatics() {
    m_compressor = new Compressor(RobotMap.PCM_MODULE_NUM);
  }

  /**
   * No default command.
   */
  @Override
  public void initDefaultCommand() {
  }


  public double getCompressorCurrent() {
    return m_compressor.getCompressorCurrent();
  }

  public boolean getPressureSwitchValue() {
    return m_compressor.getPressureSwitchValue();
  }

  public boolean getEnabled() {
    return m_compressor.enabled();
  }

  public void startCompressor(){
    m_compressor.start();
  }

  public void stopCompressor(){
    m_compressor.stop();
  }

}
