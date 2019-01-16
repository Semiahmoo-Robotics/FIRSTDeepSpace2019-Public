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
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static XboxController m_XBoxController;
  public static Joystick supportStick;
  public static Button abutton;

	/**
	 * Create a new OI and 
	 */
	public OI() {
		m_XBoxController = new XboxController(RobotMap.XBOX_PORT);		

		//Write code here to run commands for button press events
		//abutton = new JoystickButton(m_XBoxController, 1);  //Button A = 1
		//abutton.whenPressed(new DriveForward(10, 0.5, 3));
	}
	

	/** 
	 * Returns the XBoxController Instance.
	 */
	public XboxController GetXboxController() {
		return m_XBoxController;
	}	

}