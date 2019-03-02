/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.Encoder;

/**
 * A utility class which contains all of the math and data to initialize encoders,
 * and set their distance, degrees, or rotations per pulse.
 */
public final class EncoderInitialization {

  /**
   * No instantiation.
   */
  private EncoderInitialization() {
  }

  /**
   * Initializes a CIM coder for the drivetrain with the following data:
   * The gearbox ratio for the motors these CIMcoders are mounted on is 10.71:1.
    (The motor spins 10.71 times for every 1 rotation of the wheels.)
    The wheels have a diameter of 15.24 cm (6").
    20 pulses per revolution for CIMcoders
   * @param encoder the CIM coder to initialize.
   */
  public static void initDriveEncoder(Encoder encoder) {

    encoder.setMinRate(0.01); // 0.01 in/s
    encoder.setDistancePerPulse(DistancePerPulse(20, 6, 10.71)); //inches
    encoder.setReverseDirection(false);
    encoder.reset();
  }

  /**
   * Initializes a PG17 for the claw-lift joint with the following data:
   * Data is unknown yet.
   * @param encoder the PG17 to initialize.
   */
  public static void initLiftEncoder(Encoder encoder) {
    encoder.setMinRate(0.01); // 0.01 in/s
    encoder.setDistancePerPulse(RotationsPerPulse(20, 10.71)); //inches
    encoder.setReverseDirection(false);
    encoder.reset();
  }
  
  /**
   * Initializes a Aideepen encoder for the forklift mechanism with the following data:
   * The Aideepen encoder is directly mounted on the shaft of the gearbox, so the 
    gearbox ratio does not affect the encoder ticks per rotation.
    600 pulses per revolution for Aideepen Encoders.
   * @param encoder the Aideepen encoder to initialize.
   */
  public static void initAideepen(Encoder encoder) {

    encoder.setMinRate(0.01); // 0.01 RPS
    encoder.setDistancePerPulse(RotationsPerPulse(600, 1)); //rotations
    encoder.setReverseDirection(false);
    encoder.reset();
  }

  /**
   * Converts encoder pulses per revolution to distance per encoder pulse, based on the
   * wheel diameter and the gear ratio.
   * @param ppr encoder pulses per one revolution of the encoder shaft.
   * @param diameter the diameter of the wheel.
   * @param gr The gear ratio from the encoder shaft to the wheel axis.
   * @return distance of robot traveled per encoder pulse
   */
  private static double DistancePerPulse(double ppr, double diameter, double gr) {
    double distancePerRevolution = (Math.PI * diameter) / gr;
    double distancePerPulse = distancePerRevolution / ppr;
    return distancePerPulse;
  }

  /**
   * Converts encoder pulses per revolution to degrees of motor rotation per encoder pulse,
   * based on the gear ratio.
   * @param ppr encoder pulses per one revolution of the encoder shaft.
   * @param gr The gear ratio from the encoder shaft to the wheel axis.
   * @return degrees of motor rotation per encoder pulse
   */
  /*
  private static double DegreesPerPulse(double ppr, double gr) {
    double degreesperpulse = 360 / (gr * ppr);
    return degreesperpulse;
  }
  */
  /**
   * Converts encoder pulses per revolution to degrees of motor rotation per encoder pulse,
   * based on the gear ratio.
   * @param pprencoder pulses per one revolution of the encoder shaft.
   * @param gr The gear ratio from the encoder shaft to the wheel axis.
   * @return Motor Rotations Per encoder Pulse
   */
  private static double RotationsPerPulse(double ppr, double gr) {
    double rotationsperpulse = 1 / (gr * ppr);
    return rotationsperpulse;
  }
}
