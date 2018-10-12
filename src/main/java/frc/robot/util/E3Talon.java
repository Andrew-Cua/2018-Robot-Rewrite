package frc.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class E3Talon extends TalonSRX{

    

    public E3Talon(int portNumber)
    {
        super(portNumber);
        //configTalon();
    }


    public void configTalon()
    {
    this.configPeakCurrentLimit(40, 0);
    this.enableCurrentLimit(true);
    this.configContinuousCurrentLimit(40, 0); 

    this.configPeakOutputForward(1, 0);
    this.configPeakOutputReverse(-1, 0);
    }

    public void set(double pow)
    {
        super.set(ControlMode.PercentOutput, pow);
    }

    public void setFollower(E3Talon masterTalonSRX)
    {
        super.set(ControlMode.Follower, masterTalonSRX.getDeviceID());
    }

    public void setPID(int kSlot, double kP, double kI, double kD, int kTimeoutMs)
    {
        super.config_kP(kSlot, kP, kTimeoutMs);
        super.config_kI(kSlot, kI, kTimeoutMs);
        super.config_kD(kSlot, kD, kTimeoutMs);
    }

    public void setEncoder(int kSlot, int kTimeoutMs)
    {
        super.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kSlot, kTimeoutMs);
    }




}