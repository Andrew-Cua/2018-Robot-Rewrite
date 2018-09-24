/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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


  TalonSRX frontLeftTalon,
           backLeftTalon,
           frontRightTalon,
           backRightTalon;

  private static DriveTrain_Subsys driveTrain_Subsys;

   public DriveTrain_Subsys()
   {
     frontLeftTalon  = new TalonSRX(RobotMap.frontLeftMotor);
     backLeftTalon   = new TalonSRX(RobotMap.backLeftMotor);
     frontRightTalon = new TalonSRX(RobotMap.frontRightMotor);
     backRightTalon  = new TalonSRX(RobotMap.backRightMotor);

     frontLeftTalon.setInverted(true);
     backLeftTalon.setInverted(true);

     backLeftTalon.set(ControlMode.Follower, frontLeftTalon.getDeviceID());
     frontRightTalon.set(ControlMode.Follower, frontRightTalon.getDeviceID());

     Robot.m_TalonConfigurer.configTalon(frontLeftTalon);
     Robot.m_TalonConfigurer.configTalon(backLeftTalon);
     Robot.m_TalonConfigurer.configTalon(frontRightTalon);
     Robot.m_TalonConfigurer.configTalon(backRightTalon);

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

     frontLeftTalon.set(ControlMode.PercentOutput, leftPower);
     frontRightTalon.set(ControlMode.PercentOutput, rightPower);
     
     
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
