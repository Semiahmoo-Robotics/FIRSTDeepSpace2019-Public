/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchHolder;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.SensorAlign;
import frc.robot.subsystems.UltrasonicSensor;
import frc.robot.subsystems.ClimbPiston;

/**
 * Robot java source code for Team 6458 Semiahmoo Robotics
 * FRC 2019 Deep Space Season
 */
public class Robot extends TimedRobot {
  Command m_autonomousCommand;
  
  //Declare Subsystem (Initialization)
  public static OI oi;
  public static Drivetrain drivetrain;
  public static CargoIntake cargoIntake;
  public static ClimbPiston climbPiston;
  public static Pneumatics pneumatics;
  public static SensorAlign sensorAlign;
  public static HatchHolder hatchHolder;
  public static Elevator elevator;

  public SendableChooser<String> m_autoChooser;
  public SendableChooser<Boolean> m_driveChooser;

  /**
   * Called once when robot is first started up
   */
  @Override
  public void robotInit() {
    
    //Create Subsystem Objects (Initialization)
    drivetrain = new Drivetrain();
    cargoIntake = new CargoIntake();
    pneumatics = new Pneumatics();
    climbPiston = new ClimbPiston();
    sensorAlign = new SensorAlign();
    hatchHolder = new HatchHolder();
    elevator = new Elevator();
    
    oi = new OI();
    
    //AutoChooser
    m_autoChooser = new SendableChooser<String>();
    m_autoChooser.setDefaultOption("Default - Drive forward", "Blue1lv1-ShipHatchFrontLeft");
    SmartDashboard.putData("Auto mode", m_autoChooser);

    //Tank or Arcade chooser
    m_driveChooser = new SendableChooser<Boolean>();
    m_driveChooser.setDefaultOption("Default - Tank Drive", false);
    m_driveChooser.addOption("Arcade Drive", true);
    SmartDashboard.putData("Drive Mode", m_driveChooser);

    //Other initializations
    climbPiston.retractFront();
    climbPiston.retractBack();
    climbPiston.retractMedium();

    //Cammera connected to RPi. If connected on RoboRIO, take off comment.
    //CameraSetup.setupDefaultCamera(); 
  }

  /**
   * Called every robot packet, no matter the mode.
   */
  @Override
  public void robotPeriodic() {
    drivetrain.arcadeEnabled = (Boolean) m_driveChooser.getSelected();
  }

  /**
   * Called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

    /**
   * Called periodically during Disabled mode.
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

  /**
   * Called once before autonomous.
   */
  @Override
  public void autonomousInit() {
    String autoCommand = (String) m_autoChooser.getSelected();
    drivetrain.startPathWeaverAuto(autoCommand);
  }

  /**
   * Called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

    /**
   * Called once before teleop, aka driver control.
   */
  @Override
  public void teleopInit() {
    //Causes error
    //drivetrain.StopNotifyer();
    drivetrain.stop();
  }

  /**
   * Called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }

  /**
   * Log interesting values to SmartDashboard / Shuffleboard
   */
  public void log() {
    //Drivetrain
    SmartDashboard.putNumber("Gyro value", drivetrain.getGyroAngle());
    SmartDashboard.putNumber("Left Encoder", drivetrain.getLEncoder().getDistance());
    SmartDashboard.putNumber("Right Encoder", drivetrain.getREncoder().getDistance());
    SmartDashboard.putNumber("Left Motor", drivetrain.getLSpark().getSpeed());
    SmartDashboard.putNumber("Right Motor", drivetrain.getRSpark().getSpeed());
    SmartDashboard.putBoolean("Boost Engaged", drivetrain.getBoostEngaged());
    SmartDashboard.putBoolean("Precision Engaged", drivetrain.getPrecisionEngaged());

    //Pneumatics
    SmartDashboard.putBoolean("Pressure Switch", pneumatics.getPressureSwitchValue());
    SmartDashboard.putBoolean("Enabled?", pneumatics.getEnabled());

    //Ultrasonic Proximity
    SmartDashboard.putNumber("Voltage", UltrasonicSensor.GetVoltage());
    SmartDashboard.putNumber("Engineering Units", UltrasonicSensor.getDistance());
    
    //Color Sensor
    SmartDashboard.putNumber("Red", sensorAlign.getRed());
    SmartDashboard.putNumber("Blue", sensorAlign.getBlue());
    SmartDashboard.putNumber("Green", sensorAlign.getGreen());

  }
  

}
