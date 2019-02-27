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

	/**
	 * Create a new OI and 
	 */
	public OI() {
		m_p1XBox = new XboxController(RobotMap.XBOX_1P_PORT);		
		
		/*
		Drivetrain: Controlled by drive commands.
		P1 L R Joysticks. Tank/Arcade
		P1 L Trigger. Boost mode
		P1 R Trigger. Precision mide
		*/

		/*
		Forklift: Controlled in OI.
		P2 Btn 1~8. Height Presets
		P2 Joystick X. Manual Height change.
		*/

		/*
		Cargo Intake: Controlled in OI.
		P1 
		*/

		p1_abtn = new JoystickButton(m_p1XBox, 1); 	//btn A = 1
		p1_abtn.whenPressed(new SetElevator());

		p1_bbtn = new JoystickButton(m_p1XBox, 2);	//btn B = 2
		p1_bbtn.whenPressed(new ChangeHatchFormation());

		p1_xbtn = new JoystickButton(m_p1XBox, 3);	//btn X = 3
		p1_xbtn.whenPressed(new ExtendClimb());

		p1_ybtn = new JoystickButton(m_p1XBox, 4);	//btn Y = 4
		p1_ybtn.whenPressed(new RetractClimb());


		p1_lbumper = new JoystickButton(m_p1XBox, 5);	//L Bumper = 5
		p1_lbumper.whenPressed(new CargoIn());

		p1_rbumper = new JoystickButton(m_p1XBox, 6);	//R Bumper = 6
		p1_rbumper.whenPressed(new CargoOut());

		SmartDashboard.putData("Extend Climb Piston", new ExtendClimb());
		SmartDashboard.putData("Retract Climb Piston", new RetractClimb());
		SmartDashboard.putData("Start Or Stop Compressor", new StartStopCompressor());
		SmartDashboard.putData("Extend Small Climb Piston", new ExtendSmallClimb());
		SmartDashboard.putData("Retract Small Climb Piston", new RetractSmallClimb());

	}
	

	/** 
	 * Returns the XBoxController Instance.
	 */
	public XboxController GetXboxController() {
		return m_p1XBox;
	}	

}