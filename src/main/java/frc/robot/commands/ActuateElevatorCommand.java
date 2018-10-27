/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class ActuateElevatorCommand extends Command {
  public ActuateElevatorCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_Elevator_Subsys);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    //switch(Robot.m_oi.getDrivetype())
    //{
      //case DUO:
        Robot.m_Elevator_Subsys.actuateElevator(Robot.m_oi.elevatorControlDouble(Robot.m_oi.getIntakeController()));
        //System.out.println("Setting Duo Elevator");
        //SmartDashboard.putNumber("Elevator Power", Robot.m_oi.elevatorControlDouble(Robot.m_oi.getIntakeController()) );
        //break;
      //case SOLO:
        //Robot.m_Elevator_Subsys.actuateElevator(Robot.m_oi.elevatorControlDouble(Robot.m_oi.getDriveController()));
        //System.out.println("Setting Solo Elevator");
        //SmartDashboard.putNumber("Elevator Power", Robot.m_oi.elevatorControlDouble(Robot.m_oi.getDriveController()));
        //break;

    //}
    

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
    Robot.m_Elevator_Subsys.stopElevator();
  }
}
