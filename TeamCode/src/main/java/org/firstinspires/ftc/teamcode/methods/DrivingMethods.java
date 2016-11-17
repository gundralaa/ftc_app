package org.firstinspires.ftc.teamcode.methods;

import org.firstinspires.ftc.teamcode.opmodes.HardwareBot;

import java.util.Hashtable;

/**
 * Created by naisan on 10/30/16.
 */
public class DrivingMethods {
    public static Hashtable<String, Float> omniDrive(float xAxis, float yAxis, float turn) {
        Float xAxisSquare = xAxis*xAxis;
        Float yAxisSquare = yAxis*yAxis;
        Float turnSquare =  turn*turn;
        if (xAxis < 0) {
            xAxisSquare = -xAxisSquare;
        }
        if (yAxis < 0) {
            yAxisSquare = -yAxisSquare;
        }
        if (turn < 0)
            turnSquare = -turnSquare;

        Hashtable<String, Float> valuesTable = new Hashtable<>();
        valuesTable.put("XValue", xAxisSquare);
        valuesTable.put("YValue", yAxisSquare);
        valuesTable.put("TurnValue", turnSquare);

        return valuesTable;
    }

    public static void setAllMotors(HardwareBot bot, double power) {
        bot.frontLeft.setPower(power);
        bot.frontRight.setPower(power);
        bot.backLeft.setPower(power);
        bot.backRight.setPower(power);
    }

    public static void turnClockwise(HardwareBot bot, double power) {
        bot.frontLeft.setPower(power);
        bot.frontRight.setPower(-power);
        bot.backLeft.setPower(power);
        bot.backRight.setPower(-power);
    }

    public static void turnCounterClockwise(HardwareBot bot, double power) {
        bot.frontLeft.setPower(-power);
        bot.frontRight.setPower(power);
        bot.backLeft.setPower(-power);
        bot.backRight.setPower(power);
    }
}
