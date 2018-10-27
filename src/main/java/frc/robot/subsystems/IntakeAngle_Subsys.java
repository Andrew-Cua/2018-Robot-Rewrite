/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.util.*;


/**
 * Add your docs here.
 */
public class IntakeAngle_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public Spark angleControlMotor;
  private static IntakeAngle_Subsys intakeAngle_Subsys;
  private IntakeAngle_Subsys()
  {
    angleControlMotor = new Spark(RobotMap.intakeAngleMotor);
   // Robot.m_TalonConfigurer.configTalon(angleControlMotor);
  }

  public void raiseIntake()
  {
    angleControlMotor.set(1);
  }
  public void lowerIntake()
  {
    angleControlMotor.set(-1);
  }
  public void noPower()
  {
    angleControlMotor.set(0);
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
