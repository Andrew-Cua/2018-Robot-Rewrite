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
import frc.robot.commands.ActuateElevatorCommand;

/**
 * Add your docs here.
 */
public class Elevator_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX elevatorMotor;

  private static Elevator_Subsys elevator_Subsys;

  public Elevator_Subsys()
  {
    elevatorMotor = new TalonSRX(RobotMap.elevatorMotor);

    Robot.m_TalonConfigurer.configTalon(elevatorMotor);

  }

  
  public void actuateElevator(double pow)
  {
    elevatorMotor.set(ControlMode.PercentOutput, pow);
  }

  public void raiseElevator()
  {
    elevatorMotor.set(ControlMode.PercentOutput, 1);
  }

  public void lowerElevator()
  {
    elevatorMotor.set(ControlMode.PercentOutput, -1);
  }

  public void stopElevator()
  {
    elevatorMotor.set(ControlMode.PercentOutput, 0);
  }

  public static Elevator_Subsys getInstance()
  {
    if(elevator_Subsys == null)
      elevator_Subsys = new Elevator_Subsys();
    return elevator_Subsys;
  }








  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ActuateElevatorCommand());
  }
}
