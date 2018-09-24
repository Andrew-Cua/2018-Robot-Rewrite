package frc.robot.util;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class E3Talon{

    private static E3Talon talonConfigurer;

    public E3Talon()
    {
    }


    public void configTalon(TalonSRX talon)
    {
    talon.configPeakCurrentLimit(40, 0);
    talon.enableCurrentLimit(true);
    talon.configContinuousCurrentLimit(40, 0); 

    talon.configPeakOutputForward(1, 0);
    talon.configPeakOutputReverse(-1, 0);
    }

    public static E3Talon getInstance()
    {
        if(talonConfigurer == null)
            talonConfigurer = new E3Talon();
        return talonConfigurer;   
    }


}