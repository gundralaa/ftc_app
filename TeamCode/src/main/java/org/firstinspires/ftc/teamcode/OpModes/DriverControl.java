package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Methods.DrivingMethods;

/**
 * Created by abhin on 9/28/2016.
 */
@TeleOp(name = "DriverControl", group = "Competition")
public class DriverControl extends OpMode {
        final float INCREMENT = 0.1f;
        float motorPowerA = 0.0f;
        float motorPowerB = 0.0f;
        double wheelAngle;
        boolean wheelVisible;
        double legoAngle;
        boolean legoVisible;
    //Create Hardware Object

        HardwareBot TestBot = new HardwareBot();
        DrivingMethods drive = new DrivingMethods(TestBot);
        CameraFunction cameraF;
        int colorNumber;
        double values [] = new double[2];


        public void init() {
            //Initialize all hardware through method
            TestBot.init(hardwareMap);
            //cameraF = new CameraFunction(TestBot);
            //telemetry.addData("Status", "Image Targets");
            //cameraF.beacons.activate();
            //telemetry.addData("Status", "Init");

        }

        public void loop() {
            // Get Joystick values
            float leftValue = gamepad1.right_stick_y;
            float rightValue = gamepad1.left_stick_y;

            values = drive.square(leftValue,rightValue);

            //Set power of Drive Motors
            drive.tankDrive(values[0],values[1]);

            //Set Power of Peripherals
            if(gamepad2.left_bumper) {
                TestBot.leftClaw.setPosition(1.0);
                TestBot.rightClaw.setPosition(0.0);
            } else{
                TestBot.leftClaw.setPosition(0.0);
                TestBot.rightClaw.setPosition(1.0);
            }
            if(gamepad2.y) {
                TestBot.MotorB.setPower(1.0);
            } else {
                TestBot.MotorB.setPower(0.0);
            }

            if(gamepad2.a) {
                TestBot.leftPusher.setPosition(0.0);
            } else {
                TestBot.leftPusher.setPosition(1.0);
            }

            if(gamepad2.b) {
                TestBot.rightPusher.setPosition(1.0);
            } else {
                TestBot.rightPusher.setPosition(0.0);
            }

            //colorS.enableLed(false);
            //colorNumber = colorS.getColorNumber();
            /*
            telemetry.addData("Color Number", colorNumber);
            telemetry.addData("Red:", colorS.colorDecisionRed());
            telemetry.addData("Blue:", colorS.colorDecisionBlue());
            telemetry.addData("LeftODS",TestBot.lineRight.getLightDetected());
            */
            //telemetry.addData("Range Sensor", TestBot.rangeSensor.cmUltrasonic());
            /*
            wheelAngle = cameraF.getAngle(0);
            wheelVisible = cameraF.getIsVisible(0);
            legoAngle = cameraF.getAngle(2);
            legoVisible = cameraF.getIsVisible(2);

            telemetry.addData("Wheels Angle", wheelAngle);
            telemetry.addData("Wheel Visibility", wheelVisible);
            telemetry.addData("Lego Angle", legoAngle);
            telemetry.addData("Lego Visible", legoVisible);
            */


        }

}
