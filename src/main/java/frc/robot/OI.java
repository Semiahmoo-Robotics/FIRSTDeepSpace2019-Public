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

  public static XboxController m_XBoxController;
  public static Joystick supportStick;
  public static Button abutton;
  public static Button bbutton;
  public static Button xbutton;
  public static Button ybutton;
  public static Button lbumper;
  public static Button rbumper;
  public static POVButton ubutton;
  public static POVButton dbutton;

	/**
	 * Create a new OI and 
	 */
	public OI() {
		m_XBoxController = new XboxController(RobotMap.XBOX_PORT);		
		
		abutton = new JoystickButton(m_XBoxController, 1); 	//Button A = 1
		abutton.whenPressed(new ExtendRetractClaw());

		bbutton = new JoystickButton(m_XBoxController, 2);	//Button B = 2
		bbutton.whenPressed(new ChangeHatchFormation());

		xbutton = new JoystickButton(m_XBoxController, 3);	//Button X = 3
		xbutton.whenPressed(new ExtendClimb());

		ybutton = new JoystickButton(m_XBoxController, 4);	//Button Y = 4

		lbumper = new JoystickButton(m_XBoxController, 5);	//L Bumper = 5
		lbumper.whenPressed(new PullInBox());

		rbumper = new JoystickButton(m_XBoxController, 6);	//R Bumper = 6
		rbumper.whenPressed(new PullOutBox());

		ubutton = new POVButton(m_XBoxController, 0);		//Up on D pad = 0
		ubutton.whenPressed(new PIDLiftUpDown());

		dbutton = new POVButton(m_XBoxController, 180);		//Down on D pad = 180
		dbutton.whenPressed(new PIDLiftUpDown());

		SmartDashboard.putData("Extend Climb Piston", new ExtendClimb());
		SmartDashboard.putData("Retract Climb Piston", new RetractClimb());
		SmartDashboard.putData("Extend Claw Piston", new ExtendClaw());
		SmartDashboard.putData("Retract Claw Piston", new RetractClaw());
		SmartDashboard.putData("Start Or Stop Compressor", new StartStopCompressor());
	}
	

	/** 
	 * Returns the XBoxController Instance.
	 */
	public XboxController GetXboxController() {
		return m_XBoxController;
	}	

}