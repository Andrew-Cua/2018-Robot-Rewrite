/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.util.*;
import frc.robot.commands.*;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Drivetype drive = Drivetype.DUO;

  public Button collectBoxButton  = new JoystickButton(getDriveController(), 5),
  shootBoxButton    = new JoystickButton(getDriveController(), 6),
  raiseIntakeButton = new JoystickButton(getDriveController(), 4),
  lowerIntakeButton = new JoystickButton(getDriveController(), 1);
 
  public OI()
  {
    setDriver(Drivetype.DUO);
    //PIDButton.whenPressed(new PIDDriveCommand());
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  private XboxController driveController = new XboxController(RobotMap.driveController);
  private XboxController intakeController = new XboxController(RobotMap.intakeController);

/*public Button collectBoxButton  = new JoystickButton(intakeController,5),
              shootBoxButton    = new JoystickButton(intakeController,6),
              raiseIntakeButton = new JoystickButton(intakeController,3),
              lowerIntakeButton = new JoystickButton(intakeController,1);
public Button PIDButton = new JoystickButton(driveController, 2); */

 


  public void setDriver(Drivetype drive)
  {
    switch(drive)
    {
      case SOLO:
        collectBoxButton  = new JoystickButton(driveController, 5);
        shootBoxButton    = new JoystickButton(driveController, 6);
        raiseIntakeButton = new JoystickButton(driveController, 4);
        lowerIntakeButton = new JoystickButton(driveController, 1);
        collectBoxButton.whileHeld(new CollectBoxCommand());
        shootBoxButton.whileHeld(new ShootBoxCommand());
        raiseIntakeButton.whileHeld(new RaiseIntakeCommand());
        lowerIntakeButton.whileHeld(new LowerIntakeCommand());
        //System.out.println("Setting Solo");
        break;
      case DUO:
        collectBoxButton  = new JoystickButton(intakeController, 5);
        shootBoxButton    = new JoystickButton(intakeController, 6);
        raiseIntakeButton = new JoystickButton(intakeController, 4);
        lowerIntakeButton = new JoystickButton(intakeController, 1);
        collectBoxButton.whileHeld(new CollectBoxCommand());
        shootBoxButton.whileHeld(new ShootBoxCommand());
        raiseIntakeButton.whileHeld(new RaiseIntakeCommand());
        lowerIntakeButton.whileHeld(new LowerIntakeCommand());
        //System.out.println("Setting Duo");
        break;
    } 
  }

  public double elevatorControlDouble(XboxController vatorController)
  {
    double raise = vatorController.getTriggerAxis(Hand.kLeft);
    double lower = -vatorController.getTriggerAxis(Hand.kRight);
    return raise + lower;
  }


  public XboxController getIntakeController()
  {
    return intakeController;
  }

  public Drivetype getDrivetype()
  {
    return drive;
  }

  public void setDrivertype(Drivetype drive)
  {
    this.drive = drive;
  }

  public void updateControls()
  {
    setDriver(getDrivetype());
  }
  
  public XboxController getDriveController()
  {
  return driveController;
  }

}
