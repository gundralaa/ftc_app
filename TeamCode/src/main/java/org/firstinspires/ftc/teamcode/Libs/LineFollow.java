package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/2/2016.
 */
public class LineFollow {
    HardwareBot bot = new HardwareBot();
    static double highValue;
    static double lowValue;
    public LineFollow (HardwareBot bot){
        this.bot = bot;
    }

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
    public void proportionalFollow(boolean breakCondition, double gain){
        double error;
        double midValue = (highValue + lowValue)/2;
        double lighValue = bot.lineRight.getRawLightDetected();
        error = midValue - lighValue;




    }
    public void driveTillLine(double power, double threshold){

        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (bot.lineRight.getRawLightDetected() > threshold){
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);


    }
    public void driveTillLine(double power){
        double threshold = (highValue + lowValue)/2;
        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (bot.lineRight.getRawLightDetected() > threshold){
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);
    }
}
