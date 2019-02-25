/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * A class ported over from 2018.
 * <p>
 * Using an {@link edu.wpi.first.wpilibj.AnalogInput}, this class outputs distance based on an ultrasonic rangefinder
 * such as the <a href=https://www.maxbotix.com/Ultrasonic_Sensors/MB1010.htm>MB1010 LV-MaxSonar-EZ1</a>.
 */
public class UltrasonicSensor {

  private static final AnalogInput ultrasonicsensor = new AnalogInput(0);
  private static final double DEFAULT_SCALING_FACTOR = 5.0 / 1024.0;

  public static double GetVoltage() {
    return ultrasonicsensor.getAverageVoltage();
  }
  
  public static double getDistance() {
    return 5 * (ultrasonicsensor.getAverageVoltage() * DEFAULT_SCALING_FACTOR);
  }
}
