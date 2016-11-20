package org.firstinspires.ftc.teamcode.Methods;

/**
 * Created by naisan on 10/30/16.
 */

public class SensorMethods {

    final static int RADIUS = 2;
    final static int CPR = 1120;
    final static double CIRCUMFERENCE = Math.PI * RADIUS * 2;

    /*
    public static void followColoredLine(HardwareBot hardwareBot, float lineHsvValues[], double distanceToRun) {
        //This may not work in which case I will adjust it to use encoders directly in this method
        distanceWithEncoders(hardwareBot, distanceToRun, 0);

        float leftHsvValues[] = {0, 0, 0};
        float rightHsvValues[] = {0, 0, 0};

        Color.RGBToHSV(hardwareBot.lineLeft.red() * 8, hardwareBot.lineLeft.green() * 8, hardwareBot.lineLeft.blue() * 8, leftHsvValues);
        Color.RGBToHSV(hardwareBot.lineRight.red() * 8, hardwareBot.lineRight.green() * 8, hardwareBot.lineRight.blue() * 8, rightHsvValues);

        while (hardwareBot.frontRight.isBusy() ||
               hardwareBot.frontLeft.isBusy() ||
               hardwareBot.backRight.isBusy() ||
               hardwareBot.backLeft.isBusy()
              ) {

             //if the right color sensor sees the line, turn to the right
            if (
                    (rightHsvValues[0] >= (lineHsvValues[0] - 5) && rightHsvValues[0] <= (lineHsvValues[0] + 5)) &&
                    (rightHsvValues[1] >= (lineHsvValues[1] - 5) && rightHsvValues[1] <= (lineHsvValues[1] + 5)) &&
                    (rightHsvValues[2] >= (lineHsvValues[2] - 5) && rightHsvValues[2] <= (lineHsvValues[0] + 5))
               ) {

                hardwareBot.backLeft.setPower(1);
                hardwareBot.backRight.setPower(0.5);
                hardwareBot.frontLeft.setPower(1);
                hardwareBot.frontRight.setPower(0.5);

                Color.RGBToHSV(hardwareBot.lineLeft.red() * 8, hardwareBot.lineLeft.green() * 8, hardwareBot.lineLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(hardwareBot.lineRight.red() * 8, hardwareBot.lineRight.green() * 8, hardwareBot.lineRight.blue() * 8, rightHsvValues);

             //if the left color sensor sees the line, turn to the left
            } else if (
                        (leftHsvValues[0] >= (lineHsvValues[0] - 5) && leftHsvValues[0] <= (lineHsvValues[0] + 5)) &&
                        (leftHsvValues[1] >= (lineHsvValues[1] - 5) && leftHsvValues[1] <= (lineHsvValues[1] + 5)) &&
                        (leftHsvValues[2] >= (lineHsvValues[2] - 5) && leftHsvValues[2] <= (lineHsvValues[0] + 5))
                    ) {

                hardwareBot.backLeft.setPower(0.5);
                hardwareBot.backRight.setPower(1);
                hardwareBot.frontLeft.setPower(0.5);
                hardwareBot.frontRight.setPower(1);

                Color.RGBToHSV(hardwareBot.lineLeft.red() * 8, hardwareBot.lineLeft.green() * 8, hardwareBot.lineLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(hardwareBot.lineRight.red() * 8, hardwareBot.lineRight.green() * 8, hardwareBot.lineRight.blue() * 8, rightHsvValues);
            //if neither color sensor sees the line, continue forward
            } else {

                hardwareBot.backLeft.setPower(1);
                hardwareBot.backRight.setPower(1);
                hardwareBot.frontLeft.setPower(1);
                hardwareBot.frontRight.setPower(1);

                Color.RGBToHSV(hardwareBot.lineLeft.red() * 8, hardwareBot.lineLeft.green() * 8, hardwareBot.lineLeft.blue() * 8, leftHsvValues);
                Color.RGBToHSV(hardwareBot.lineRight.red() * 8, hardwareBot.lineRight.green() * 8, hardwareBot.lineRight.blue() * 8, rightHsvValues);
            }
        }
    }


        public static void distanceWithEncoders(HardwareBot hardwareBot, double distanceInInches, double power) {
            int encoder_counts = (int)(distanceInInches / CIRCUMFERENCE) * CPR;

            hardwareBot.backLeft.setTargetPosition(encoder_counts);
            hardwareBot.backRight.setTargetPosition((encoder_counts));
            hardwareBot.frontLeft.setTargetPosition((encoder_counts));
            hardwareBot.frontRight.setTargetPosition((encoder_counts));

            hardwareBot.backLeft.setPower(power);
            hardwareBot.backRight.setPower(power);
            hardwareBot.frontLeft.setPower(power);
            hardwareBot.frontRight.setPower(power);
        }
        */
}
