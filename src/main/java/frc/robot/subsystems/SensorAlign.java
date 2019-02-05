/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class SensorAlign extends Subsystem {

  protected final static int CMD = 0x80;
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
  private I2C sensor;

  private ByteBuffer buffy = ByteBuffer.allocate(8);

  public short red = 0, green = 0, blue = 0, prox = 0; //public not private!!!
  public ColorSensor(I2C.Port port) {

    //initialize objects
    buffy.order(ByteOrder.LITTLE_ENDIAN);
    sensor = new I2C(port, 0x39); //0x39 is the address of the Vex ColorSensor V2
    
    sensor.write(CMD | 0x00, PON | AEN | PEN);
    
    sensor.write(CMD | 0x01, (int) (256-integrationTime/2.38)); //configures the integration time (time for updating color data)
    sensor.write(CMD | 0x0E, 0b1111);
      
  }

  //called by an outside class to update red, green and blue. these variables are public.
  public void read() { 
    buffy.clear();
    sensor.read(CMD | MULTI_BYTE_BIT | RDATA_REGISTER, 8, buffy);
    
    red = buffy.getShort(0);
    if(red < 0) { red += 0b10000000000000000; }
    
    green = buffy.getShort(2);
    if(green < 0) { green += 0b10000000000000000; }
    
    blue = buffy.getShort(4); 
    if(blue < 0) { blue += 0b10000000000000000; }
    
    prox = buffy.getShort(6); 
    if(prox < 0) { prox += 0b10000000000000000; }
      
  }
  
  public int status() {
    buffy.clear();
    sensor.read(CMD | 0x13, 1, buffy);
    return buffy.get(0);
  }
  
  public void free() {
    sensor.free();
  }

  public void drive() {
    read();
    //insert movement command here
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new drive());
  }
}
