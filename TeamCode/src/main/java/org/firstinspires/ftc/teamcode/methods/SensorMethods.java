package org.firstinspires.ftc.teamcode.methods;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.HardwareBot;

/**
 * Created by naisan on 10/30/16.
 */

public class SensorMethods {

    final static int RADIUS = 2;
    final static int CPR = 1120;
    final static double CIRCUMFERENCE = Math.PI * RADIUS * 2;


    public static void followColoredLine(HardwareBot hardwareBot, float lineLight, double distanceToRun) {
        //This may not work in which case I will adjust it to use encoders directly in this method
        distanceWithEncoders(hardwareBot, distanceToRun, 0);

        while (hardwareBot.frontRight.isBusy() ||
               hardwareBot.frontLeft.isBusy() ||
               hardwareBot.backRight.isBusy() ||
               hardwareBot.backLeft.isBusy()
              ) {
            //if the robot is on the line, proceed slowly, veering left
            if(hardwareBot.lineRight.getLightDetected() == lineLight) {
                hardwareBot.frontLeft.setPower(0.2);
                hardwareBot.frontRight.setPower(0.3);
                hardwareBot.backLeft.setPower(0.2);
                hardwareBot.backRight.setPower(0.3);
            //if the robot is not on the line, proceed slowly, veering right
            } else if(hardwareBot.lineRight.getLightDetected() != lineLight + 5 ||
               hardwareBot.lineRight.getLightDetected() != lineLight - 5) {
                hardwareBot.frontLeft.setPower(0.3);
                hardwareBot.frontRight.setPower(0.2);
                hardwareBot.backLeft.setPower(0.3);
                hardwareBot.backRight.setPower(0.2);
            }
        }
    }

    public static void stopAtColoredLine(HardwareBot bot, float lineLight) {
        DrivingMethods.setAllMotors(bot, 0.5);
        while(bot.lineRight.getLightDetected() != lineLight);
        DrivingMethods.setAllMotors(bot, 0);
    }


    public static void distanceWithEncoders(HardwareBot hardwareBot, double distanceInInches, double power) {
        int encoder_counts = (int)(distanceInInches / CIRCUMFERENCE) * CPR;

        int bl_pos = hardwareBot.backLeft.getCurrentPosition();
        int br_pos = hardwareBot.backRight.getCurrentPosition();
        int fl_pos = hardwareBot.frontLeft.getCurrentPosition();
        int fr_pos = hardwareBot.frontRight.getCurrentPosition();

        hardwareBot.backLeft.setTargetPosition(bl_pos +encoder_counts);
        hardwareBot.backRight.setTargetPosition(br_pos + encoder_counts);
        hardwareBot.frontLeft.setTargetPosition(fl_pos + encoder_counts);
        hardwareBot.frontRight.setTargetPosition(fr_pos + encoder_counts);

        hardwareBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DrivingMethods.setAllMotors(hardwareBot, power);

        while(hardwareBot.backLeft.isBusy() || hardwareBot.backRight.isBusy() || hardwareBot.frontLeft.isBusy() || hardwareBot.frontRight.isBusy());

        DrivingMethods.setAllMotors(hardwareBot, 0);

        hardwareBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardwareBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardwareBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardwareBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static boolean beaconRed(HardwareBot bot) {
        byte [] sensorCache;
        sensorCache = bot.beaconSensorReader.read(0x04, 1);
        int colorNumber = (sensorCache[0] & 0xff);
        if(colorNumber == 10 || colorNumber == 11) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean beaconBlue(HardwareBot bot) {
        byte [] sensorCache;
        sensorCache = bot.beaconSensorReader.read(0x04, 1);
        int colorNumber = (sensorCache[0] & 0xff);
        if(colorNumber == 2 || colorNumber == 3) {
            return true;
        } else {
            return false;
        }
    }
}
