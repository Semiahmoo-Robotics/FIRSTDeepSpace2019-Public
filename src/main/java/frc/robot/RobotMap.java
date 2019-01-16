/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */


public class RobotMap {

  //Motor Ports on ROBOrio
  public static final int LEFT_DRIVE_PORT = 1;
  public static final int RIGHT_DRIVE_PORT = 0;
  public static final int LEFT_INTAKE_PORT = 2;
  public static final int RIGHT_INTAKE_PORT = 3;

   //TODO Later finalize robotmap ports
  public static final int R_ENCODER_PORT_CHA = 2;
  public static final int R_ENCODER_PORT_CHB = 3;
  public static final int L_ENCODER_PORT_CHA = 4;
  public static final int L_ENCODER_PORT_CHB = 5;


  //Controller ports
  public static final int XBOX_PORT = 0;
  public static final int LOGITECH_PORT = 1;

  //when boost is Engaged, max output is occured. otherwise, the MULTIPLIER takes effect
  public static final double MULTIPLIER = 0.7;


  //Choose tank or arcade
  public static boolean DefaultArcadeDrive = false;

}
