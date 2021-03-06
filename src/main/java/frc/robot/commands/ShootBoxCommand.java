/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.*;
public class ShootBoxCommand extends Command {
 public ShootBoxCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_Intake_Subsys);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_Intake_Subsys.stopIntake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   /* switch(Robot.m_oi.getDrivetype())
    {
      case SOLO:
        Robot.m_oi.setDriver(Drivetype.SOLO);
        break;
      case DUO:
        Robot.m_oi.setDriver(Drivetype.DUO);
        break;
    }*/
    Robot.m_Intake_Subsys.shootBox();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_Intake_Subsys.stopIntake();
  }
}
