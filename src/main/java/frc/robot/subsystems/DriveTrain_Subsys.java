/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.util.*;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class DriveTrain_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public E3Talon frontLeftTalon,
           backLeftTalon,
           frontRightTalon,
           backRightTalon;

  private static DriveTrain_Subsys driveTrain_Subsys;

   private DriveTrain_Subsys()
   {
     
     frontLeftTalon  = new E3Talon(RobotMap.frontLeftMotor);
     backLeftTalon   = new E3Talon(RobotMap.backLeftMotor);
     frontRightTalon = new E3Talon(RobotMap.frontRightMotor);
     backRightTalon  = new E3Talon(RobotMap.backRightMotor);

     frontLeftTalon.setInverted(true);
     backLeftTalon.setInverted(true);

     //backLeftTalon.set(ControlMode.Follower,frontLeftTalon.getDeviceID());
     //backRightTalon.set(ControlMode.Follower, frontRightTalon.getDeviceID());

     initEncoders(0, 100);
     setPID(0.001, 0.001, 0, 0, 100);
     //Robot.m_TalonConfigurer.configTalon(frontLeftTalon);
     //Robot.m_TalonConfigurer.configTalon(backLeftTalon);
     //Robot.m_TalonConfigurer.configTalon(frontRightTalon);
     //Robot.m_TalonConfigurer.configTalon(backRightTalon);

   }


   public void drive(double yVal, double xVal)
   {
    // double yVal   = Robot.m_oi.getDriveController().getY(Hand.kLeft);
     //double xVal   = Robot.m_oi.getDriveController().getX(Hand.kRight);

     double sensFactor = 0.75D;

     double yValPrime = Math.pow((sensFactor*yVal), 3) + ((1-sensFactor)*yVal);
     double xValPrime = Math.pow((sensFactor*xVal), 3) + ((1-sensFactor)*xVal);

     double leftPower =  yValPrime - xValPrime;
     double rightPower = yValPrime + xValPrime;
     if(Robot.m_oi.getDriveController().getAButton())
      resetEncoders(0, 100);

     frontLeftTalon.set(ControlMode.PercentOutput,leftPower);
     frontRightTalon.set(ControlMode.PercentOutput, rightPower);
     printEncoderValue();
     //System.out.println(leftPower);
     //System.out.println(rightPower);
     
     
   }

  public void drivePID(double inches)
  {
    double ticks = distanceToTicks(inches);
    System.out.println(ticks);
    System.out.println(backRightTalon.getSensorCollection().getQuadraturePosition());
    backRightTalon.set(ControlMode.Position, ticks);
    frontRightTalon.setFollower(backRightTalon);
    //frontLeftTalon.setInverted(true);
    frontLeftTalon.setFollower(backRightTalon);
    //backLeftTalon.setInverted(true);
    backLeftTalon.setFollower(backRightTalon);
  }

   public void setPID(double kP, double kI, double kD, int slot, int timeOut)
   {
    backLeftTalon.setPID(slot, kP, kI, kD, timeOut);
    backRightTalon.setPID(slot, kP, kI, kD, timeOut);
   }

   public void initEncoders(int slot, int timeOut)
   {
     backLeftTalon.setEncoder(slot, timeOut);
     backLeftTalon.setSensorPhase(true);
     backRightTalon.setEncoder(slot, timeOut);
     backRightTalon.setSensorPhase(false);
     
   }
   private double distanceToTicks(double inches)
   {
     double distancePerRev = Math.PI*9;
     double revNeeded = inches/distancePerRev;
     return revNeeded*(1024*4);
   }

   public void resetEncoders(int slot, int timeoutMs)
   {
     backLeftTalon.getSensorCollection().setQuadraturePosition(0, 10);
     backRightTalon.getSensorCollection().setQuadraturePosition(0, 10);
   }
   
   public void TeleopInit()
   {
     
     backLeftTalon.setFollower(frontLeftTalon);
     backRightTalon.setFollower(frontRightTalon);
   }

   private void printEncoderValue()
   {
     //int pulseWidthPositionLeft = backLeftTalon.getSensorCollection().getQuadraturePosition();
     //System.out.println(pulseWidthPositionLeft+": "+ pulseWidthPositionLeft/(1024*4));
     int pulseWidthPositionRight = backRightTalon.getSensorCollection().getQuadraturePosition();
     System.out.println(pulseWidthPositionRight+": "+ pulseWidthPositionRight/(1024*4));
   }

   public static DriveTrain_Subsys getInstance()
   {
     if(driveTrain_Subsys == null)
      driveTrain_Subsys = new DriveTrain_Subsys();
    return driveTrain_Subsys;
   }





  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }
}
