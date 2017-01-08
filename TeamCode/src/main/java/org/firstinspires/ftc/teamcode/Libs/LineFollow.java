package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by abhin on 11/2/2016.
 */
public class LineFollow {
    LinearOpMode opMode;
    HardwareBot bot;
    public static double highValue = 0.6;
    public static double lowValue = 0.1;
    public LineFollow (HardwareBot bot, LinearOpMode opMode){
        this.bot = bot;
        this.opMode = opMode;

    }

    public void setHighValue(double highValue){
        this.highValue = highValue;
    }
    public void setLowValue(double lowValue){
        this.lowValue = lowValue;
    }

    public void simpleFollow(double power){
        double midValue = 0.4;
        double reflectedLight = bot.lineFront.getLightDetected();
        if (reflectedLight < midValue){
            bot.FrontLeft.setPower(0);
            bot.BackLeft.setPower(0);
            bot.FrontRight.setPower(-power);
            bot.BackRight.setPower(-power);
        }
        if (reflectedLight > midValue){
            bot.FrontLeft.setPower(-power);
            bot.BackLeft.setPower(-power);
            bot.FrontRight.setPower(0);
            bot.BackRight.setPower(0);
        }
        if (reflectedLight == midValue){
            bot.FrontLeft.setPower(-power);
            bot.BackLeft.setPower(-power);
            bot.FrontRight.setPower(-power);
            bot.BackRight.setPower(-power);
        }
    }
    public void proportionalFollow(boolean breakCondition, double gain, double diffConstant){
        double error;
        double midValue = (highValue + lowValue)/2;
        double powerValue;
        double lighValue = bot.lineFront.getRawLightDetected();
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

        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (opMode.opModeIsActive() && bot.lineBack.getLightDetected() < threshold){
            opMode.telemetry.addData("Sensor Value", bot.lineBack.getLightDetected());
            opMode.telemetry.update();
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);


    }

    public void turnCCLine(double power, double threshold){
        bot.FrontLeft.setPower(-power);
        bot.BackLeft.setPower(-power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);

        while (opMode.opModeIsActive() && bot.lineFront.getLightDetected() < threshold){
            opMode.telemetry.addData("Sensor Value", bot.lineFront.getLightDetected());
            opMode.telemetry.update();
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);
    }
    public void turnCLine(double power, double threshold){
        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(-power);
        bot.BackRight.setPower(-power);

        while (opMode.opModeIsActive() && bot.lineFront.getLightDetected() < threshold){
            opMode.telemetry.addData("Sensor Value", bot.lineFront.getLightDetected());
            opMode.telemetry.update();
        }

        bot.FrontLeft.setPower(0);
        bot.BackLeft.setPower(0);
        bot.FrontRight.setPower(0);
        bot.BackRight.setPower(0);
    }

}
