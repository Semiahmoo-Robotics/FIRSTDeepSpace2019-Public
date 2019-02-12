/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ReadColor;

/**
 * Refer to the data sheets for the Register Address
 * http://www.revrobotics.com/content/docs/TMD3782_v2.pdf
 */
public class SensorAlign extends Subsystem {

  protected final static int COMMAND_REGISTER_BIT = 0x80;
  protected final static int MULTI_BYTE_BIT = 0x20;

  protected final static int ENABLE_REGISTER  = 0x00;
  protected final static int ATIME_REGISTER   = 0x01;
  protected final static int PPULSE_REGISTER  = 0x0E;

  protected final static int ID_REGISTER     = 0x12;
  protected final static int CDATA_REGISTER  = 0x14;
  protected final static int RDATA_REGISTER  = 0x16;
  protected final static int GDATA_REGISTER  = 0x18;
  protected final static int BDATA_REGISTER  = 0x1A;
  protected final static int PDATA_REGISTER  = 0x1C;

  protected final static int PON   = 0b00000001;
  protected final static int AEN   = 0b00000010;
  protected final static int PEN   = 0b00000100;
  protected final static int WEN   = 0b00001000;
  protected final static int AIEN  = 0b00010000;
  protected final static int PIEN  = 0b00100000;
  
  private I2C colorsensor;

  private ByteBuffer buffy = ByteBuffer.allocate(8);

  public short red = 0, green = 0, blue = 0, prox = 0; //public not private!!!
  
  public SensorAlign() {

    //initialize objects
    colorsensor = new I2C(I2C.Port.kOnboard, 0x39); //0x39 is the device address of the Vex ColorSensor V2
    buffy.order(ByteOrder.LITTLE_ENDIAN);

    colorsensor.write(COMMAND_REGISTER_BIT | 0x00, PON | AEN | PEN); //Enable the color sensor
    //colorsensor.write(COMMAND_REGISTER_BIT | 0x0E, 0b1111); // Specifies the number of proximity pulses to be generated.
      
  }

  //called by an outside class to update red, green and b lue. these variables are public.
  public void read() { 
    buffy.clear();
    colorsensor.read(COMMAND_REGISTER_BIT | MULTI_BYTE_BIT | RDATA_REGISTER, 8, buffy);
    
    red = buffy.getShort(0);
    if(red < 0) { red += 0b10000000000000000; }
    
    green = buffy.getShort(2);
    if(green < 0) { green += 0b10000000000000000; }
    
    blue = buffy.getShort(4); 
    if(blue < 0) { blue += 0b10000000000000000; }
    
    prox = buffy.getShort(6); 
    if(prox < 0) { prox += 0b10000000000000000; }
    
    if(red + green + blue >= 1000){ //the sum of the color represents the brightness of the reflection
      Robot.oi.GetXboxController().setRumble(kLeftRumble, 0.5);
    }else{
      Robot.oi.GetXboxController().setRumble(kLeftRumble, 0);
    }
    
  }

  public short getRed(){
    return this.red;
  }

  public short getBlue(){
    return this.blue;
  }

  public short getGreen(){
    return this.green;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ReadColor());
  }
}
