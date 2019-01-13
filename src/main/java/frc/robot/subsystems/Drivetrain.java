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
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TankDrive;

/**
 * DriveTrain chassis subsystem code.
 * Differential drive with 6 wheels, 2 OR 4 motors
 */

public class Drivetrain extends Subsystem {

  private final Spark m_LeftDrive;
  private final Spark m_RightDrive;

  private final DifferentialDrive m_Chassis;

  private final Encoder m_REncoder;
  private final Encoder m_LEncoder;
  private final AnalogGyro m_Gyro;

    //boost mode
  public boolean boostEngaged = false;

  /**
   * Constructor - Create a new DriveTrain class.
   */
  public Drivetrain() {
    
    //initialize objects
    m_LeftDrive = new Spark(RobotMap.LEFT_DRIVE_PORT);
    m_LeftDrive.setInverted(true);
    m_RightDrive = new Spark(RobotMap.RIGHT_DRIVE_PORT);
    m_RightDrive.setInverted(true);
    m_Chassis = new DifferentialDrive(m_LeftDrive, m_RightDrive);

    m_REncoder = new Encoder(RobotMap.R_ENCODER_PORT_CHA, RobotMap.R_ENCODER_PORT_CHB, false, EncodingType.k4X);
    m_LEncoder = new Encoder(RobotMap.L_ENCODER_PORT_CHA, RobotMap.L_ENCODER_PORT_CHB, false, EncodingType.k4X);
    m_Gyro = new AnalogGyro(RobotMap.GYRO_PORT);


    //Stops motor if the robot loses connection to the driver station.
    m_Chassis.setSafetyEnabled(true);

    //For now, reduce max output for safety. Change to 1 later.
    //m_Chassis.setMaxOutput(0.5);

    initializeEncoder(m_LEncoder);
    initializeEncoder(m_REncoder);

  }

  /**
   * When other commands aren't using the drivetrain,
   * the default command will be teleop drive with joystick
   */
  @Override
  public void initDefaultCommand() {
    if (RobotMap.DefaultArcadeDrive) {
      setDefaultCommand(new ArcadeDrive());
    } else {
      setDefaultCommand(new TankDrive());
    }
  }

  /**
   * Tank drive using two values individualy
   * 
   * @param left Left joystick/drive value
   * @param right Right joystick/drive value
   */
  public void TankDriveSet(Double left, Double right){
    m_Chassis.tankDrive(left, right);
  }

    /**
   * Tank drive using xBoxController instance.
   * 
   * @param controller xBoxController to use as input.
   */
  public void TankDriveSet(XboxController controller){
    m_Chassis.tankDrive(controller.getY(Hand.kLeft), controller.getY(Hand.kRight));
  }

  /**
   * Arcade drive using two values individualy
   * 
   * @param x x joystick/drive value
   * @param z z joystick/drive value
   */
  public void ArcadeDriveSet(Double x, Double z){
    m_Chassis.arcadeDrive(x, z);
  }

  /**
   * Arcade drive using joystick instance
   * 
   * @param joystick joystick to use as input.
   */
  public void ArcadeDriveSet(XboxController joystick){ 
    m_Chassis.arcadeDrive(joystick.getY(), joystick.getX());
  }

  public AnalogGyro getGyro(){
    return m_Gyro;
  }

  public Encoder getLEncoder(){
    return m_LEncoder;
  }

  public Encoder getREncoder(){
    return m_REncoder;
  }

  public Spark getLSpark(){
    return m_LeftDrive;
  }

  public Spark getRSpark(){
    return m_RightDrive;
  }

  /** 
   * Curvature Drive using two values
   * 
   * @param speed desired speed of robot
   * @param rotation desired rotation rate of robot
  */
  public void CurvatureDriveSet(double speed, double rotation) {
    m_Chassis.curvatureDrive(speed, rotation, false);
  }

  private void initializeEncoder(Encoder encoder) {
    encoder.setMaxPeriod(0.1); //0.1 sec
    encoder.setMinRate(0.01); // 0.01 m/s
    encoder.setDistancePerPulse(encoderPresets());
    encoder.setReverseDirection(false);
    encoder.setSamplesToAverage(7);
  }
  
  /**
   * Stop the drivetrain from moving.
   */
  public void stop(){
    m_Chassis.tankDrive(0, 0);
    m_Chassis.arcadeDrive(0, 0);
  }

  public double encoderPresets() {
    
    //TODO Check if correct. lAST YEAR'S DATA
    //The gearbox ratio for the motors these CIMcoders are mounted on is 10.71:1.
    //(The motor spins 10.71 times for every 1 rotation of the wheels.)
    //The wheels have a diameter of 15.24 cm (6").
    //20 pulses per revolution for CIMcoders
    //the values are in the instance are in metres

    double pulsesPerRevolution = 20;
    double distancePerRevolution = (Math.PI * 0.1524) / 10.71;
    double distancePerPulse = distancePerRevolution / pulsesPerRevolution;
    return distancePerPulse;
  }

}