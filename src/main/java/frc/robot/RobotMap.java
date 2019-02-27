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

  //PWM ports on ROBOrio
  public static final int L_DRIVE = 0;
  public static final int R_DRIVE = 1;
  public static final int L_INTAKE = 9;
  public static final int R_INTAKE = 4;
  public static final int HATCHHOLDER = 2;
  public static final int FORKLIFT = 3;

  //DIO ports on ROBOrio
  public static final int L_ENCODER_CHA = 0;
  public static final int L_ENCODER_CHB = 1;
  public static final int R_ENCODER_CHA = 2;
  public static final int R_ENCODER_CHB = 3;
  public static final int LIFTCODER_CHA = 4;
  public static final int LIFTCODER_CHB = 5;

  //PCM ports
  public static final int PCM_MODULE = 0;

  public static final int R_CLIMB_RVSE = 4;
  public static final int R_CLIMB_FWD = 5;
  public static final int CLAW_REVERSE_CHN = 0;
  public static final int CLAW_FORWARD_CHN = 1;
  public static final int L_CLIMB_RVSE = 6;
  public static final int L_CLIMB_FWD = 7;

  //Path Weaver Constants
  //number of encoder counts per wheel revolution for the drivetrain
  public static final int TICK_PER_REV = 1024;
  //diameter of the wheels of the drivetrain in the chosen unit value (in I believe).
  //The wheels have a diameter of 15.24 cm (6").
  public static final double WHEEL_DIAMETER = 6;
  //maximum velocity of the robot
  public static final double MAX_VELOCITY = 10;

  public static final String PATH_NAME = "example";

  //Controller ports
  public static final int XBOX_1P_PORT = 0;
  public static final int KEYBOARD_2P_PORT = 1;
  public static final int SUPPORT_2P_PORT = 2;

}
