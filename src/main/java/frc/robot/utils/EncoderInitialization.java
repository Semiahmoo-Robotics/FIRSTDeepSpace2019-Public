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
  public static void initializeCIMcoder(Encoder encoder) {

    encoder.setMinRate(0.01); // 0.01 in/s
    encoder.setDistancePerPulse(DistancePerPulseForWheels(20, 6, 10.71)); //inches
    encoder.setReverseDirection(false);
    encoder.reset();
  }

  /**
   * Initializes a PG17 for the claw-lift joint with the following data:
   * Data is unknown yet.
   * @param encoder the PG17 to initialize.
   */
  public static void initializePG17(Encoder encoder) {
    /*Data is unknown yet. */
    encoder.reset();
  }
  
  //for PID lift encoder
  /**
   * Initializes a Aideepen encoder for the forklift mechanism with the following data:
   * The Aideepen encoder is directly mounted on the shaft of the gearbox, so the 
    gearbox ratio does not affect the encoder ticks per rotation.
    600 pulses per revolution for Aideepen Encoders.
   * @param encoder the Aideepen encoder to initialize.
   */
  public static void initializeAideepen(Encoder encoder) {
    /* */

    encoder.setMinRate(0.01); // 0.01 RPS
    encoder.setDistancePerPulse(RotationsPerPulse(600, 1)); //inches
    encoder.setReverseDirection(false);
    encoder.reset();
  }


  private static double DistancePerPulseForWheels(double pulsesPerRevolution, double wheeldiameter, double gearratio) {
    double distancePerRevolution = (Math.PI * wheeldiameter) / gearratio;
    double distancePerPulse = distancePerRevolution / pulsesPerRevolution;
    return distancePerPulse;
  }

  private static double DegreesPerPulse(double pulsesPerRevolution, double gearratio) {
    double degreesperpulse = 360 / (gearratio * pulsesPerRevolution);
    return degreesperpulse;
  }

  private static double RotationsPerPulse(double pulsesPerRevolution, double gearratio) {
    double rotationsperpulse = 1 / (gearratio * pulsesPerRevolution);
    return rotationsperpulse;
  }
}
