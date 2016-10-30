package org.firstinspires.ftc.teamcode.Methods;

import android.graphics.Color;

import org.firstinspires.ftc.teamcode.Hardware.Motors;
import org.firstinspires.ftc.teamcode.Hardware.Sensors;
import org.firstinspires.ftc.teamcode.Hardware.Servos;

/**
 * Created by naisan on 10/30/16.
 */
public class SensorMethods {
    //TO DO: Add and refactor other methods from FreshMethods.java
    public static void follow_colored_line(Motors motors, Sensors sensors, Servos servos, float lineHsvValues[], float distanceToRun) {
        //TO DO: Add encoders to this method.
        float leftHsvValues[] = {0, 0, 0};
        float rightHsvValues[] = {0, 0, 0};
        Color.RGBToHSV(sensors.colorSensorLeft.red() * 8, sensors.colorSensorLeft.green() * 8, sensors.colorSensorLeft.blue() * 8, leftHsvValues);
        Color.RGBToHSV(sensors.colorSensorRight.red() * 8, sensors.colorSensorRight.green() * 8, sensors.colorSensorRight.blue() * 8, rightHsvValues);

        while () {
            if ((rightHsvValues[0] >= (lineHsvValues[0] - 5) && rightHsvValues[0] <= (lineHsvValues[0] + 5)) &&
                    (rightHsvValues[1] >= (lineHsvValues[1] - 5) && rightHsvValues[1] <= (lineHsvValues[1] + 5)) &&
                    (rightHsvValues[2] >= (lineHsvValues[2] - 5) && rightHsvValues[2] <= (lineHsvValues[0] + 5))) { //if the right color sensor sees the line, turn to the right
                motors.backLeft.setPower(1);
                motors.backRight.setPower(0.5);
                motors.frontLeft.setPower(1);
                motors.frontRight.setPower(0.5);
                Color.RGBToHSV(sensors.colorSensorLeft.red() * 8, sensors.colorSensorLeft.green() * 8, sensors.colorSensorLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(sensors.colorSensorRight.red() * 8, sensors.colorSensorRight.green() * 8, sensors.colorSensorRight.blue() * 8, rightHsvValues);
            } else if ((leftHsvValues[0] >= (lineHsvValues[0] - 5) && leftHsvValues[0] <= (lineHsvValues[0] + 5)) &&
                    (leftHsvValues[1] >= (lineHsvValues[1] - 5) && leftHsvValues[1] <= (lineHsvValues[1] + 5)) &&
                    (leftHsvValues[2] >= (lineHsvValues[2] - 5) && leftHsvValues[2] <= (lineHsvValues[0] + 5))) { //if the left color sensor sees the line, turn to the left
                motors.backLeft.setPower(0.5);
                motors.backRight.setPower(1);
                motors.frontLeft.setPower(0.5);
                motors.frontRight.setPower(1);
                Color.RGBToHSV(sensors.colorSensorLeft.red() * 8, sensors.colorSensorLeft.green() * 8, sensors.colorSensorLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(sensors.colorSensorRight.red() * 8, sensors.colorSensorRight.green() * 8, sensors.colorSensorRight.blue() * 8, rightHsvValues);
            } else {
                motors.backLeft.setPower(1);
                motors.backRight.setPower(1);
                motors.frontLeft.setPower(1);
                motors.frontRight.setPower(1);
                Color.RGBToHSV(sensors.colorSensorLeft.red() * 8, sensors.colorSensorLeft.green() * 8, sensors.colorSensorLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(sensors.colorSensorRight.red() * 8, sensors.colorSensorRight.green() * 8, sensors.colorSensorRight.blue() * 8, rightHsvValues);
            }
        }
    }
}
