/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.utils.EncoderInitialization;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Forklift extends PIDSubsystem {
  
  private final Spark m_liftmotor = new Spark(RobotMap.FORKLIFT);
  private final Encoder m_liftcoder = 
  new Encoder(RobotMap.R_ENCODER_CHA, RobotMap.R_ENCODER_CHB, false, EncodingType.k2X);  
  
  /**
   * Add your docs here.
   */
  public Forklift() {

    // Intert a subsystem name and PID values here
    super("Forklift", 1.0, 0.0, 0.0);
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);

    EncoderInitialization.initializeAideepen(m_liftcoder);

    setSetpoint(0); //Sets where the PID controller should move the system to
    enable(); //Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    //none
  }

  @Override
  protected double returnPIDInput() {
    return m_liftcoder.get(); // returns the sensor value that is providing the feedback for the system
  }

  @Override
  protected void usePIDOutput(double output) {
    m_liftmotor.set(output); // this is where the computed output value fromthe PIDController is applied to the motor
  }

}
