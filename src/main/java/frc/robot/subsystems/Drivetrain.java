/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import frc.robot.utils.EncoderInitialization;
import frc.robot.utils.Utils;

import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;

/**
 * DriveTrain chassis subsystem.
 * Differential (Tank) drive with 6 wheels.
 * 4 motor controlers wired to 2 pwm roborio ports.
 * 2 cimCoders (encoders) wired to the roborio.
 */
public class Drivetrain extends Subsystem {

  private final Spark m_ldrive;
  private final Spark m_rdrive;

  private final DifferentialDrive m_chassis;

  private final Encoder m_rencoder;
  private final Encoder m_lencoder;
  private final ADXRS450_Gyro m_gyro;

  private EncoderFollower m_lfollower;
  private EncoderFollower m_rfollower;
  
  private Notifier m_pathWeaverfollower_notifyer;

  public final double NORMAL_MULTIPLIER = 0.75;
  public final double PRECISION_MULTIPLIER = 0.5;

  //boost mode - when true, makes the robot's max speed to 100%. Apples no MULTIPLIERs.
  public boolean boostEngaged = false;
  //presicion mode - when true, makes the robot's max speed to 50%. Apples PRECISION_MULTIPLIER.
  public boolean precisionEngaged = false;
  //If boost & precision is false, then NORMAL_MULTIPLIER is applied.

  /**
   * Constructor - Create a new DriveTrain class.
   */
  public Drivetrain() {
    
    //initialize objects
    m_ldrive = new Spark(RobotMap.L_DRIVE);
    m_ldrive.setInverted(true);
    m_rdrive = new Spark(RobotMap.R_DRIVE);
    m_rdrive.setInverted(true);
    m_chassis = new DifferentialDrive(m_ldrive, m_rdrive);
    
    m_rencoder = new Encoder(RobotMap.R_ENCODER_CHA, RobotMap.R_ENCODER_CHB, false, EncodingType.k2X); /* CIMcoders */
    m_lencoder = new Encoder(RobotMap.L_ENCODER_CHA, RobotMap.L_ENCODER_CHB, false, EncodingType.k2X); /* CIMcoders */
    m_gyro = new ADXRS450_Gyro(/* No port. This default constructor uses the built-in port where the gyro sits. */);


    //Stops motor if the robot loses connection to the driver station.
    m_chassis.setSafetyEnabled(true);

    EncoderInitialization.initializeCIMcoder(m_lencoder);
    EncoderInitialization.initializeCIMcoder(m_rencoder);

    m_gyro.calibrate();

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
   * gets the truncated m_Gyro angle value.
   * @return the gyro angle value.
   */
  public double getGyroAngle() {
    return Utils.truncateDecimal(m_gyro.getAngle(), 3);
  }

  /**
   * gets the m_LEncoder instance.
   * @return m_LEncoder
   */
  public Encoder getLEncoder(){
    return m_lencoder;
  }

  /**
   * gets the m_REncoder instance.
   * @return m_REncoder
   */
  public Encoder getREncoder(){
    return m_rencoder;
  }
  
  /**
   * gets the m_LeftDrive spark instance.
   * @return m_LeftDrive
   */
  public Spark getLSpark(){
    return m_ldrive;
  }

  
  /**
   * gets the m_RightDrive spark instance.
   * @return m_RightDrive
   */
  public Spark getRSpark(){
    return m_rdrive;
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
   * Sets precisionEngaged to parameter's value
   * @param boostEngaged The value you want to set precisionEngaged to
   */
  public void setPrecisionEngaged(boolean precisionEngaged){
    this.precisionEngaged = precisionEngaged;
  }

  /**
   * Gets current this.precisionEngaged
   * @return The value this.precisionEngaged is set to.
   */
  public boolean getPrecisionEngaged(){
    return precisionEngaged;
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

  /**
   * Starts an Autonomous pre-made Path. The paths are made using the Pathweaver tool.
   * @param pathName
   */
  public void startPathWeaverAuto(String pathName) {
    /*
    PathWeaver currently has a known issue. The left and right paths are being swapped.
    This will be fixed in PathWeaver v2019.3.1. This is why the file name is reversed for
    each trajectory.
    */
    Trajectory left_trajectory = PathfinderFRC.getTrajectory(pathName + ".right");
    Trajectory right_trajectory = PathfinderFRC.getTrajectory(pathName + ".left");

    m_lfollower = new EncoderFollower(left_trajectory);
    m_rfollower = new EncoderFollower(right_trajectory);

    m_lfollower.configureEncoder(m_lencoder.get(), RobotMap.TICK_PER_REV, RobotMap.WHEEL_DIAMETER);
    // You must tune the PID values next line (line 239)!
    m_lfollower.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_VELOCITY, 0);

    m_rfollower.configureEncoder(m_rencoder.get(), RobotMap.TICK_PER_REV, RobotMap.WHEEL_DIAMETER);
    // Same PID value tuning.
    m_rfollower.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_VELOCITY, 0);
    
    m_pathWeaverfollower_notifyer = new Notifier(this::pathWeaverFollowPath);
    m_pathWeaverfollower_notifyer.startPeriodic(left_trajectory.get(0).dt);
  }

  public void pathWeaverFollowPath() {

    if (m_lfollower.isFinished() || m_rfollower.isFinished()) {
      m_pathWeaverfollower_notifyer.stop();
    } else {
      double lspeed = m_lfollower.calculate(m_lencoder.get());
      double rspeed = m_rfollower.calculate(m_rencoder.get());
      double heading = m_gyro.getAngle();
      //TODO Because of a bug, Depending on the orientation of your gyro, may also need to invert the desired heading.
      double desired_heading = Pathfinder.r2d(m_lfollower.getHeading());
      double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
      double turn =  0.8 * (-1.0 / 80.0) * heading_difference;

      m_ldrive.set(lspeed + turn);
      m_rdrive.set(rspeed - turn);
    }
  }

  public void StopNotifyer() {
    Robot.drivetrain.m_pathWeaverfollower_notifyer.stop();
  }

}