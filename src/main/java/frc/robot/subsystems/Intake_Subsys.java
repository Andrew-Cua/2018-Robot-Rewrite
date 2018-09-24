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
import frc.robot.commands.StopIntakeCommand;

/**
 * Add your docs here.
 */
public class Intake_Subsys extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public Spark  leftIntakeMotor, rightIntakeMotor;

  private static Intake_Subsys intake_Subsys;

  public Intake_Subsys()
  {
    leftIntakeMotor = new Spark(RobotMap.leftintakeMotor);
    rightIntakeMotor = new Spark(RobotMap.rightIntakeMotor);

    leftIntakeMotor.setInverted(true);
  }

  public void collectBox()
  {
    leftIntakeMotor.set(-1);
    rightIntakeMotor.set(-1);
  }

  public void shootBox()
  {
    leftIntakeMotor.set(1);
    rightIntakeMotor.set(1);
  }

  public void stopIntake()
  {
    leftIntakeMotor.set(0);
    rightIntakeMotor.set(0);
  }

  public static Intake_Subsys getInstance()
  {
    if(intake_Subsys == null)
      intake_Subsys = new Intake_Subsys();
    return intake_Subsys;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new StopIntakeCommand());
  }
}
