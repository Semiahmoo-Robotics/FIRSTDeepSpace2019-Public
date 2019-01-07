/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick m_Joystick = new Joystick(RobotMap.JOYSTICK_PORT);
	
	public Joystick getJoystick() {
		return m_Joystick;

	}

  public static XboxController m_XBoxController = new XboxController(RobotMap.XBOX_PORT);
  //maybe use logitech stick? lol
  public static Joystick SupportStick;

	public OI() {
		//TODO write commands here
	}
	

	public XboxController GetXboxController() {
		return m_XBoxController;
	}	

}