package org.firstinspires.ftc.teamcode.Methods;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libs.HardwareBot;

import java.util.Hashtable;

/**
 * Created by naisan on 10/30/16.
 */
public class DrivingMethods {
    HardwareBot bot;
    public DrivingMethods(HardwareBot bot){
        this.bot =  bot;
    }
    //leftStick is always 0
    //rightStick is always 1
    public static double[] square(double leftStick, double rightStick) {
        double [] values;
        values = new double[2];
        double leftStickSquare= leftStick*leftStick;
        double rightStickSquare = rightStick*rightStick;
        if (leftStick < 0) {
            leftStickSquare = -leftStickSquare;
        }
        if (rightStick < 0) {
            rightStickSquare = -rightStickSquare;
        }
        values [0] = leftStickSquare;
        values [1] = rightStickSquare;

        return values;
    }

    public static double[] sinCurve(double leftStick, double rightStick, double highValue ){
        double [] values;
        values = new double[2];
        values [0] = Math.sin(((Math.PI/2)*leftStick)/highValue);
        values [1] = Math.sin(((Math.PI/2)*rightStick)/highValue);
        return values;
    }

    public void tankDrive(double leftY, double rightY){

        bot.FrontRight.setPower(leftY);
        bot.BackRight.setPower(leftY);
        bot.FrontLeft.setPower(rightY);
        bot.BackLeft.setPower(rightY);

    }

    public void arcadeDrive(double yStick, double xStick){
        double leftValue = yStick - xStick;
        double rightValue = yStick + xStick;

        leftValue = Range.clip(leftValue,-1,1);
        rightValue = Range.clip(rightValue,-1,1);

        bot.FrontLeft.setPower(leftValue);
        bot.BackLeft.setPower(leftValue);
        bot.FrontRight.setPower(rightValue);
        bot.BackRight.setPower(rightValue);
    }

}
