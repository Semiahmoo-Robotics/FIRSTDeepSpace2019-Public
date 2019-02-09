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
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.SensorAlign;
import frc.robot.subsystems.CargoClaw;
import frc.robot.subsystems.ClimbPiston;
import frc.robot.utils.*;

/**
 * Robot java source code for Team 6458 Semiahmoo Robotics
 * FRC 2019 Deep Space Season
 */
public class Robot extends TimedRobot {
  Command m_autonomousCommand;
  
  //Declare Subsystem (Initialization)
  public static OI oi;
  public static Drivetrain drivetrain;
  public static Intake intake;
  public static CargoClaw cargoClaw;
  public static ClimbPiston climbPiston;
  public static Pneumatics pneumatics;
  public static SensorAlign sensorAlign;

  SendableChooser<Command> autoChooser;

  /**
   * Called once when robot is first started up
   */
  @Override
  public void robotInit() {
    
    //Create Subsystem Objects (Initialization)
    drivetrain = new Drivetrain();
    intake = new Intake();
    cargoClaw = new CargoClaw();
    pneumatics = new Pneumatics();
    climbPiston = new ClimbPiston();
    sensorAlign = new SensorAlign();
    
    oi = new OI();
    
    
    //put data to smartdashboard
    SmartDashboard.putData(drivetrain);
    SmartDashboard.putData(cargoClaw);
    SmartDashboard.putData(pneumatics);
    SmartDashboard.putData(climbPiston);

 
    //TODO Set Default Auto
    //autoChooser = new SendableChooser<>();
    //autoChooser.setDefaultOption("Default - Drive forward", new DriveForward(10, 0.5, 5));
    //autoChooser.addOption("My Auto", new TurnRightLeft(50, 0.6));
    //SmartDashboard.putData("Auto mode", autoChooser);

    drivetrain.getGyro().calibrate();
    CameraSetup.setupDefaultCamera();
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
    drivetrain.getGyro().reset();
    m_autonomousCommand = autoChooser.getSelected();
    m_autonomousCommand.start();
    drivetrain.getGyro().reset();
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
    // This makes sure that the autonomous stops running when teleop starts running.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    drivetrain.SetMaxspeed()
    drivetrain.getGyro().reset();
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
    //DriveForward MoveTest = new DriveForward();
    //MoveTest 
  }

  /**
   * Log interesting values to SmartDashboard / Shuffleboard
   */
  public void log() {
    SmartDashboard.putNumber("Gyro value", drivetrain.getGyro().getAngle());
    SmartDashboard.putNumber("Left Encoder", drivetrain.getLEncoder().getDistance());
    SmartDashboard.putNumber("Right Encoder", drivetrain.getREncoder().getDistance());
    SmartDashboard.putNumber("Left Motor", drivetrain.getLSpark().getSpeed());
    SmartDashboard.putNumber("Right Motor", drivetrain.getRSpark().getSpeed());
    SmartDashboard.putBoolean("Boost Engaged", drivetrain.getBoostEngaged());
    SmartDashboard.putBoolean("Pressure Switch Value", pneumatics.getPressureSwitchValue());
    SmartDashboard.putBoolean("Enabled?", pneumatics.getEnabled());

    SmartDashboard.putNumber("Red", sensorAlign.getRed());
    SmartDashboard.putNumber("Blue", sensorAlign.getBlue());
    SmartDashboard.putNumber("Green", sensorAlign.getGreen());
  }

}
