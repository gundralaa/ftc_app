package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by abhin on 11/8/2016.
 */
public class DistanceSensing {
    HardwareBot bot = new HardwareBot();
    LinearOpMode opMode;
    static double mvalue = 56 ;
    static double cvalue = -0.756;
    private double rawLValue;
    private double linearLValue;
    private double distanceValue;
    private double convertedValue;
    public DistanceSensing (HardwareBot bot, LinearOpMode opMode) {
        this.bot = bot;
        this.opMode = opMode;
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

        while (opMode.opModeIsActive() && bot.rangeSensor.cmUltrasonic() > threshold){
            opMode.telemetry.addData("Distance:",bot.rangeSensor.cmUltrasonic());
        }

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

        while (opMode.opModeIsActive() && bot.rangeSensor.cmUltrasonic() < threshold){
            opMode.telemetry.addData("Distance", bot.rangeSensor.cmUltrasonic());
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);
    }


}
