package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/2/2016.
 */
public class LineFollow {
    HardwareBot bot = new HardwareBot();
    public static double highValue = 0.6;
    public static double lowValue = 0.1;
    public LineFollow (HardwareBot bot){
        this.bot = bot;
    }
    EncoderDrive drive = new EncoderDrive(bot);

    public void setHighValue(double highValue){
        this.highValue = highValue;
    }
    public void setLowValue(double lowValue){
        this.lowValue = lowValue;
    }

    public void simpleFollow(int numberOfSensors, boolean breakCondition, double power){
        double midValue = (highValue + lowValue)/2;
        switch (numberOfSensors){
            case 1:
                while (breakCondition){
                    double reflectedLight = bot.lineRight.getRawLightDetected();
                    if (reflectedLight > midValue){
                        bot.FrontLeft.setPower(-power);
                        bot.BackLeft.setPower(-power);
                        bot.FrontRight.setPower(0);
                        bot.BackRight.setPower(0);
                    }
                    if (reflectedLight < midValue){
                        bot.FrontLeft.setPower(0);
                        bot.BackLeft.setPower(0);
                        bot.FrontRight.setPower(-power);
                        bot.BackRight.setPower(-power);
                    }
                    if (reflectedLight == midValue){
                        bot.FrontLeft.setPower(-power);
                        bot.BackLeft.setPower(-power);
                        bot.FrontRight.setPower(-power);
                        bot.BackRight.setPower(-power);
                    }
                }
                break;
            case 2:
                while (breakCondition){
                    double reflectedLeftLight = bot.lineLeft.getLightDetected();
                    double reflectedRightLight = bot.lineRight.getLightDetected();
                    if (reflectedLeftLight > midValue || reflectedRightLight < midValue){
                        bot.FrontLeft.setPower(0);
                        bot.BackLeft.setPower(0);
                        bot.FrontRight.setPower(power);
                        bot.BackRight.setPower(power);
                    }
                    if (reflectedLeftLight < midValue || reflectedRightLight > midValue){
                        bot.FrontLeft.setPower(power);
                        bot.BackLeft.setPower(power);
                        bot.FrontRight.setPower(0);
                        bot.BackRight.setPower(0);
                    }
                    if (reflectedLeftLight == midValue || reflectedRightLight == midValue){
                        bot.FrontLeft.setPower(power);
                        bot.BackLeft.setPower(power);
                        bot.FrontRight.setPower(power);
                        bot.BackRight.setPower(power);
                    }
                }
                break;
            default:
                break;
        }
    }
    public void proportionalFollow(boolean breakCondition, double gain, double diffConstant){
        double error;
        double midValue = (highValue + lowValue)/2;
        double powerValue;
        double lighValue = bot.lineRight.getRawLightDetected();
        /*
         * If the error is negative that means
         *     The robot is over the line
         * If the error is positive
         *     The robot is over the ground
         * The error can only be between -0.5 and 0.5
         * When over the line the right is negative and left is positive
         * When over the ground the right is positive the left is negative
         */
        error = midValue - lighValue;
        powerValue = error * gain;
        // The diff constant is the speed we want when robot is going forward
        bot.FrontLeft.setPower(-powerValue + diffConstant);
        bot.BackLeft.setPower(-powerValue + diffConstant);
        bot.FrontRight.setPower(powerValue + diffConstant);
        bot.BackRight.setPower(powerValue + diffConstant);
    }
    public void driveTillLine(double power, double threshold){

        drive.straightF(power);
        while (bot.lineRight.getLightDetected() < threshold){}
        drive.stop();


    }
    public void driveTillLine(double power){
        double threshold = (highValue + lowValue)/2;
        drive.straightF(power);
        while (bot.lineRight.getRawLightDetected() > threshold){}
        drive.stop();
    }

}
