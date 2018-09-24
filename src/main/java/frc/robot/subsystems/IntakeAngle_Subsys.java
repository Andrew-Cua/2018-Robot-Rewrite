/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;


/**
 * Add your docs here.
 */
public class IntakeAngle_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public TalonSRX angleControlMotor;
  private static IntakeAngle_Subsys intakeAngle_Subsys;
  public IntakeAngle_Subsys()
  {
    angleControlMotor = new TalonSRX(RobotMap.intakeAngleMotor);
    Robot.m_TalonConfigurer.configTalon(angleControlMotor);
  }

  public void raiseIntake()
  {
    angleControlMotor.set(ControlMode.PercentOutput, 1);
  }
  public void lowerIntake()
  {
    angleControlMotor.set(ControlMode.PercentOutput, -1);
  }
  public void noPower()
  {
    angleControlMotor.set(ControlMode.PercentOutput, 0);
  }


  public static IntakeAngle_Subsys getInstance()
  {
    if(intakeAngle_Subsys == null)
      intakeAngle_Subsys = new IntakeAngle_Subsys();
    return intakeAngle_Subsys;
  }



  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultIntakeAngleCommand());
  }
}
