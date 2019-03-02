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
import frc.robot.utils.EncoderInitialization;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  private final Spark m_elevator;
  private final Encoder m_liftcoder;

  public int p = 1;
  public int i = 1;
  public int d = 1;


  public Elevator() {

    m_elevator = new Spark(RobotMap.ELEVATOR);
    m_liftcoder = new Encoder(RobotMap.LIFTCODER_CHA, RobotMap.LIFTCODER_CHB, false, EncodingType.k2X);
    EncoderInitialization.initLiftEncoder(m_liftcoder);

  }

  public void Set(double set) {
    m_elevator.set(set);
  } 

  public void Stop() {
    m_elevator.set(0);
  }

  @Override
  public void initDefaultCommand() {
    //none
  }
}
