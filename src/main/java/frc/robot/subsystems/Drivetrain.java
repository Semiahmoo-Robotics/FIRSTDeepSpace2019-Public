/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

/**
 * Drivebase chassis subsystem code.
 * Differential drive with 6 wheels, 2 OR 4 motors
 */

public class Drivetrain extends Subsystem {

  //TODO after kickoff, check if drivetrain has 2 or 4 motors for drivebase. Currently assuming 2 motors
  private final Spark m_LeftDrive = new Spark(RobotMap.LEFT_DRIVE_PORT);
  private final Spark m_RightDrive = new Spark(RobotMap.RIGHT_DRIVE_PORT);

  private final DifferentialDrive m_Chassis;

  private final Encoder m_REncoder = new Encoder(RobotMap.R_ENCODER_PORT_CHA, RobotMap.R_ENCODER_PORT_CHB, false, EncodingType.k4X);
  private final Encoder m_LEncoder = new Encoder(RobotMap.L_ENCODER_PORT_CHA, RobotMap.L_ENCODER_PORT_CHB, false, EncodingType.k4X);
  private final AnalogGyro m_Gyro = new AnalogGyro(RobotMap.GYRO_PORT);


  /**
   * Constructor - Create a new drivebase class.
   */
  public Drivetrain() {
      
    //TODO is motor inversed or not?
    //m_RightDrive.setInverted(true);

    m_Chassis = new DifferentialDrive(m_LeftDrive, m_RightDrive);

    //Stops motor if the robot loses connection to the driver station.
    m_Chassis.setSafetyEnabled(true);

    //TODO For now, reduce max output for safety. Change to 1 later.
    m_Chassis.setMaxOutput(0.1);

  }

  /**
   * When other commands aren't using the drivetrain,
   * the default command will be teleop drive with joystick
   */
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }

  /**
   * Tank drive using two values individualy
   * 
   * @param left Left joystick/drive value
   * @param right Right joystick/drive value
   */
  public void DriveSet(Double left, Double right){
    m_Chassis.tankDrive(left, right);
  }

  //Method used for controlling motors when using an autonomous command


  /**
   * Tank drive using xBoxController instance
   * 
   * @param controller xBoxController to use as input.
   */
  public void DriveSet(XboxController controller){
    m_Chassis.tankDrive(controller.getY(Hand.kLeft), controller.getY(Hand.kRight));
  }



  /**
   * Stop the drivetrain from moving.
   */
  public void stop(){
    m_Chassis.tankDrive(0, 0);
  }

}