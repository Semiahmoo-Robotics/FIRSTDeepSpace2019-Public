/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//if you want you can change the names, I was just honestly bored and needed entertainment
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

public class HiMyNameIsBarryAllenAndImTheFastestManAlive {
  private static final AnalogInput runninginthe90s = new AnalogInput(0);
  private static final double omgSoMuchMath = 1.0;
  public static double theFlash() {
    return runninginthe90s.getVoltage();
  }
  public static double getMeDatRuler() {
    return theFlash() * omgSoMuchMath;
  }
}
