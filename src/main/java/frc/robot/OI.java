/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static XboxController m_p1XBox;
  public static XboxController m_p2Keyboard;
  public static Joystick m_p2Support;

  public static Button p1_abtn;
  public static Button p1_bbtn;
  public static Button p1_xbtn;
  public static Button p1_ybtn;
  public static Button p1_lbumper;
  public static Button p1_rbumper;
  public static POVButton p1_ubtn;
  public static POVButton p1_dbtn;

  public static Button p2_btn1;
  public static Button p2_btn2;
  public static Button p2_btn3;
  public static Button p2_btn4;
  public static Button p2_btn5;
  public static Button p2_btn6;
  public static Button p2_btn7;
  public static Button p2_btn8;
  public static Button p2_btn9;
  public static Button p2_btn0;

  public static Boolean frontClimbToggle = false;
  public static Boolean backClimbToggle = false;
  public static Boolean smallClimbToggle = false;


	/**
	 * Operator Interface. It maps all of the robot commands to the input methods.
	 */
	public OI() {
		m_p1XBox = new XboxController(RobotMap.XBOX_1P_PORT);		
		m_p2Keyboard = new XboxController(RobotMap.KEYBOARD_2P_PORT);		
		m_p2Support = new Joystick(RobotMap.SUPPORT_2P_PORT);		
		
		/*
		Drivetrain: Controlled by drive commands.
		P1 L R Joysticks. Tank/Arcade
		P1 L Trigger. Boost mode
		P1 R Trigger. Precision mide
		*/

		/*
		TODO Change values of the setpoints
		Forklift: Controlled in OI.
		P2 Btn 3~9. Height Presets
		P2 Joystick X. Manual Height change.
		*/


		/*
		Cargo Intake: Controlled in OI.
		P1 Btn 5, L Bumper. Feed In
		P1 Btn 6 R Bumper. Feed Out
		*/
		p1_lbumper = new JoystickButton(m_p1XBox, 5);	//L Bumper = 5
		p1_lbumper.whenPressed(new CargoIn());

		p1_rbumper = new JoystickButton(m_p1XBox, 6);	//R Bumper = 6
		p1_rbumper.whenPressed(new CargoOut());

		/*
		Hatch Holder: Controlled in OI
		P1 Btn 2, B Button. Toggle change formation (Hold/Release)
		*/
		p1_bbtn = new JoystickButton(m_p1XBox, 2);	//btn B = 2
		p1_bbtn.whenPressed(new ChangeHatchFormation());

		/*
		Climb Piston: Controlled in OI
		P2 Btn 0. Retract or Extend Front Climb
		P2 Btn 1. Retract or Extend Back Climb
		P2 Btn 2. Retract or Extend Small Climb
		*/
		p2_btn3 = new JoystickButton(m_p2Keyboard, 3);
		p2_btn3.whenPressed(new ToggleFrontClimb());

		p2_btn1 = new JoystickButton(m_p2Keyboard, 1);
		p2_btn1.whenPressed(new ToggleBackClimb());

		p2_btn2 = new JoystickButton(m_p2Keyboard, 2);
		p2_btn2.whenPressed(new ToggleMediumClimb());

		p2_btn4 = new JoystickButton(m_p2Keyboard, 4);
		p2_btn4.whenPressed(new ToggleSmallClimb());

		//Other
		SmartDashboard.putData("Start Or Stop Compressor", new StartStopCompressor());
	}
	

	/** 
	 * Returns the XBoxController Instance.
	 */
	public XboxController getP1Xbox() {
		return m_p1XBox;
	}	

	/** 
	 * Returns the 3D Joystick Instance.
	 */
	public XboxController getSupportStick() {
		return m_p1XBox;
	}	

}