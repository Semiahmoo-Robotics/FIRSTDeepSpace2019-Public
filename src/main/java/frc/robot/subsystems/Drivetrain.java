/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

/**
 * DriveTrain chassis subsystem.
 * Differential (Tank) drive with 6 wheels.
 * 4 motor controlers wired to 2 pwm roborio ports.
 * 2 cimCoders (encoders) wired to the roborio.
 */
public class Drivetrain extends Subsystem {

  private final Spark m_lDrive;
  private final Spark m_rDrive;

  private final DifferentialDrive m_chassis;

  private final Encoder m_rEncoder;
  private final Encoder m_lEncoder;
  private final ADXRS450_Gyro m_gyro;

  private NetworkTableEntry m_maxspeed;

  //boost mode - When boostEngaged is true, it applies RobotMap.MULTIPLYER to drivebase speed.
  public boolean boostEngaged = false;

  /**
   * Constructor - Create a new DriveTrain class.
   */
  public Drivetrain() {
    
    //initialize objects
    m_lDrive = new Spark(RobotMap.L_DRIVE);
    m_lDrive.setInverted(true);
    m_rDrive = new Spark(RobotMap.R_DRIVE);
    m_rDrive.setInverted(true);
    m_chassis = new DifferentialDrive(m_lDrive, m_rDrive);
    
    m_rEncoder = new Encoder(RobotMap.R_ENCODER_CHA, RobotMap.R_ENCODER_CHB, false, EncodingType.k4X); /* CIMcoders */
    m_lEncoder = new Encoder(RobotMap.L_ENCODER_CHA, RobotMap.L_ENCODER_CHB, false, EncodingType.k4X); /* CIMcoders */
    m_gyro = new ADXRS450_Gyro(/* No port. This default constructor uses the built-in port where the gyro sits. */);


    //Stops motor if the robot loses connection to the driver station.
    m_chassis.setSafetyEnabled(true);

    initializeEncoder(m_lEncoder);
    initializeEncoder(m_rEncoder);
    m_gyro.reset();

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
  public void TankDriveSet(Double left, Double right){
    m_chassis.tankDrive(left, right);
  }

  /**
   * Tank drive using xBoxController instance
   * 
   * @param controller xBoxController to use as input.
   */
  public void TankDriveSet(XboxController controller){
    m_chassis.tankDrive(controller.getY(Hand.kLeft), controller.getY(Hand.kRight));
   
  }

  /**
   * Arcade drive using two values individualy
   * 
   * @param x x joystick/drive value
   * @param z z joystick/drive value
   */
  public void ArcadeDriveSet(Double x, Double z){
    m_chassis.arcadeDrive(x, z);
  }

  /**
   * Arcade drive using joystick instance
   * 
   * @param joystick joystick to use as input.
   */
  public void ArcadeDriveSet(XboxController joystick){ 
    m_chassis.arcadeDrive(joystick.getY(), joystick.getX());
  
  }

  /**
   * gets the m_Gyro instance.
   * @return m_Gyro
   */
  public ADXRS450_Gyro getGyro(){
    return m_gyro;
  }

  /**
   * gets the m_LEncoder instance.
   * @return m_LEncoder
   */
  public Encoder getLEncoder(){
    return m_lEncoder;
  }

  /**
   * gets the m_REncoder instance.
   * @return m_REncoder
   */
  public Encoder getREncoder(){
    return m_rEncoder;
  }
  
  /**
   * gets the m_LeftDrive spark instance.
   * @return m_LeftDrive
   */
  public Spark getLSpark(){
    return m_lDrive;
  }

  
  /**
   * gets the m_RightDrive spark instance.
   * @return m_RightDrive
   */
  public Spark getRSpark(){
    return m_rDrive;
  }

  /**
   * Sets boostEngaged to parameter's value
   * @param boostEngaged The value you want to set boostEngaged to
   */
  public void setBoostEngaged(boolean boostEngaged){
    this.boostEngaged = boostEngaged;
  }

  /**
   * Gets current this.boostEngaged
   * @return The value this.boostEngaged is set to.
   */
  public boolean getBoostEngaged(){
    return boostEngaged;
  }

  /** 
   * Curvature Drive using two values
   * 
   * @param speed desired speed of robot
   * @param rotation desired rotation rate of robot
  */
  public void CurvatureDriveSet(double speed, double rotation) {
    m_chassis.curvatureDrive(speed, rotation, false);
   
  }
  
  /** 
   * Initialize the encoders by setting various needs.
   * Run this method when encoder instances are created.
   * @param encoder the encoder which needs to be initialized
  */
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
    m_chassis.tankDrive(0, 0);
    m_chassis.arcadeDrive(0, 0);
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