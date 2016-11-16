package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/8/2016.
 */
public class DistanceSensing {
    HardwareBot bot = new HardwareBot();
    static double mvalue = 56 ;
    static double cvalue = -0.756;
    private double rawLValue;
    private double linearLValue;
    private double distanceValue;
    private double convertedValue;
    public DistanceSensing (HardwareBot bot) {
        this.bot = bot;
    }

    public void setMValue(double mvalue){
        this.mvalue = mvalue;
    }

    public void setCValue(double cvalue){
        this.cvalue = cvalue;
    }

    public double getDistance (){

        rawLValue = bot.wallDist.getRawLightDetected();
        linearLValue = Math.pow(rawLValue, -0.5);
        distanceValue = ((linearLValue*mvalue) + cvalue);
        convertedValue = (distanceValue * 0.3973);
        return convertedValue;

    }

    public void driveTillDist(double power, double threshold){

        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (bot.rangeSensor.cmUltrasonic() > threshold){}

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);

    }
    public void driveAwayDist(double power, double threshold){
        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (bot.rangeSensor.cmUltrasonic() < threshold){}

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);
    }


}
