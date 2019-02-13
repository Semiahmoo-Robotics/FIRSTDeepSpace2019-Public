/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClawArm extends Subsystem {
  
  public final Spark m_primaryArm;
  public final Spark m_secondaryArm;
  public final Encoder m_pEncoder;
  public final Encoder m_sEncoder;
  

  public ClawArm(){
    m_primaryArm = new Spark(RobotMap.P_CLAW_ARM);
    m_secondaryArm = new Spark(RobotMap.S_CLAW_ARM);

    m_pEncoder = new Encoder(RobotMap.P_ENCODER_CHA, RobotMap.P_ENCODER_CHB, false, EncodingType.k4X); /* CIMcoders */
    m_sEncoder = new Encoder(RobotMap.S_ENCODER_CHA, RobotMap.S_ENCODER_CHB, false, EncodingType.k4X); /* CIMcoders */

    initializeEncoder(m_pEncoder);
    initializeEncoder(m_sEncoder);
  }

  @Override
  public void initDefaultCommand() {
    
  }

  public void primaryMove(double speed){
    m_primaryArm.set(speed);
  }    

  public void secondaryMove(double speed){
    m_secondaryArm.set(speed);
  }

  public Encoder pEncoderGet(){
    return m_pEncoder;
  }

  public Encoder sEncoderGet(){
    return m_sEncoder;
  }

  public void stop(){
    m_primaryArm.set(0.0);
    m_secondaryArm.set(0.0);
  }

  private void initializeEncoder(Encoder encoder) {
    encoder.setMaxPeriod(0.1); //0.1 sec
    encoder.setMinRate(0.01); // 0.01 m/s
    encoder.setDistancePerPulse(encoderPresets());
    encoder.setReverseDirection(false);
    encoder.setSamplesToAverage(7);
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
