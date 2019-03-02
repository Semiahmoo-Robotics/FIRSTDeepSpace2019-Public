/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PIDElevator;
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
  public static PIDElevator elevator;
  public static PowerDistributionPanel power;

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
    elevator = new PIDElevator();
    power = new PowerDistributionPanel();
    
    oi = new OI();
    
    //put data to smartdashboard
    SmartDashboard.putData(drivetrain);
    SmartDashboard.putData(pneumatics);
    SmartDashboard.putData(climbPiston);
    

    m_autoChooser = new SendableChooser<>();
    m_autoChooser.setDefaultOption("Default - Drive forward", "Blue1lv1-ShipHatchFrontLeft");
    SmartDashboard.putData("Auto mode", m_autoChooser);

    //Tank or Arcade chooser
    m_driveChooser = new SendableChooser<Boolean>();
    m_driveChooser.setDefaultOption("Default - Tank Drive", true);
    m_driveChooser.addOption("Arcade Drive", false);
    SmartDashboard.putData("Drive Mode", m_driveChooser);

    power.clearStickyFaults();
    

    //Cammera connected to RPi. If connected on RoboRIO, take off comment.
    //CameraSetup.setupDefaultCamera(); 
  }

  /**
   * Called every robot packet, no matter the mode.
   */
  @Override
  public void robotPeriodic() {
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
    if (oi.getP1Xbox().getBackButton()) {
      cargoIntake.SetIntake(0.5);
    } else if (oi.getP1Xbox().getBButton()) {
      cargoIntake.SetIntake(-0.2);
    } else {
      cargoIntake.SetIntake(0);
    }
  }

  /**
   * Log interesting values to SmartDashboard / Shuffleboard
   */
  public void log() {
    SmartDashboard.putNumber("Gyro value", drivetrain.getGyroAngle());
    SmartDashboard.putNumber("Left Encoder", drivetrain.getLEncoder().getDistance());
    SmartDashboard.putNumber("Right Encoder", drivetrain.getREncoder().getDistance());
    SmartDashboard.putNumber("Left Motor", drivetrain.getLSpark().getSpeed());
    SmartDashboard.putNumber("Right Motor", drivetrain.getRSpark().getSpeed());
    SmartDashboard.putBoolean("Boost Engaged", drivetrain.getBoostEngaged());
    SmartDashboard.putBoolean("Pressure Switch Value", pneumatics.getPressureSwitchValue());
    SmartDashboard.putBoolean("Enabled?", pneumatics.getEnabled());
    SmartDashboard.putNumber("Units of voltage", UltrasonicSensor.GetVoltage());
    SmartDashboard.putNumber("Units of some real world distance", UltrasonicSensor.getDistance());
    SmartDashboard.putNumber("Red", sensorAlign.getRed());
    SmartDashboard.putNumber("Blue", sensorAlign.getBlue());
    SmartDashboard.putNumber("Green", sensorAlign.getGreen());
  }
  

}
