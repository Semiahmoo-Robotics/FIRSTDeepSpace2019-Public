/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicSensor {
  private static final AnalogInput ultrasonicsensor = new AnalogInput(0);
  private static final double volts_to_distance = 1.0;
  public static double GetVoltage() {
    return ultrasonicsensor.getVoltage();
  }
  public static double getDistance() {
    return GetVoltage() * volts_to_distance;
  }
}
