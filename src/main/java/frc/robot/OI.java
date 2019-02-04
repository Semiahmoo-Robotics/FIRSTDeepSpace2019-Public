/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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

	/**
	 * Create a new OI and 
	 */
	public OI() {
		m_XBoxController = new XboxController(RobotMap.XBOX_PORT);		

		//Write code here to run commands for button press events
		abutton = new JoystickButton(m_XBoxController, 1);  //Button A = 1
		abutton.whenPressed(new ExtendClaw());

		bbutton = new JoystickButton(m_XBoxController, 2);	//Button B = 2
		bbutton.whenPressed(new RetractClaw());

		xbutton = new JoystickButton(m_XBoxController, 3);	//Button X = 3
		xbutton.whenPressed(new ExtendClimb());

		ybutton = new JoystickButton(m_XBoxController, 4);	//Button Y = 4
		ybutton.whenPressed(new RetractClimb());
	}
	

	/** 
	 * Returns the XBoxController Instance.
	 */
	public XboxController GetXboxController() {
		return m_XBoxController;
	}	

}