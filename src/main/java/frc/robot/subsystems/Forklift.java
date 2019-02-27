/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.utils.EncoderInitialization;
import frc.robot.RobotMap;

/**
  * The forklift is controlled by a PID closed loop, and
  * utilizes a PID subsystem using an internal PIDcontroller.
  * Input: The rotation count from the Aideepen encoder.
  * Output: The CIM motor's PWM value.
  */
public class Forklift extends PIDSubsystem {

  //Network table value containing setpoint value. Value is currently in rotations.
  public NetworkTableEntry m_setpoint;
  
  private final Spark m_liftmotor = new Spark(RobotMap.FORKLIFT);
  private final Encoder m_liftcoder = 
  new Encoder(RobotMap.R_ENCODER_CHA, RobotMap.R_ENCODER_CHB, false, EncodingType.k2X);  
  
  public Forklift() {
    // TODO PID values go here. Need to tune!
    super("Forklift", 0.5, 0.0, 0.0);

    EncoderInitialization.initializeAideepen(m_liftcoder);

    setOutputRange(0.0, 1.0);
    setInputRange(0.0, 100.0);
    //Units are in Rotations of the motor
    setAbsoluteTolerance(0.01); //3.6 degrees

    enable(); //Enables the PID controller.
    setSetpoint(0); //Sets where the PID controller should move the system to: Initially 0 rotations.

    m_setpoint = Shuffleboard.getTab("Test").add("Set Setpoint", 0.0).getEntry();
  }

  @Override
  public void initDefaultCommand() {
    //none
  }

  @Override
  protected double returnPIDInput() {
    //Units are in Rotations of the motor
    return m_liftcoder.getDistance(); // returns the sensor value that is providing the feedback for the system
  }

  @Override
  protected void usePIDOutput(double output) {
    m_liftmotor.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
  }

}
