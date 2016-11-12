package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;

/**
 * Created by abhin on 9/28/2016.
 */
@TeleOp(name = "DriverControl", group = "Competition")
public class DriverControl extends OpMode {
        final float INCREMENT = 0.1f;
        float motorPowerA = 0.0f;
        float motorPowerB = 0.0f;
        //Create Hardware Object

        HardwareBot TestBot = new HardwareBot();
        ColorSensing colorS = new ColorSensing(TestBot);
        int colorNumber;


        public void init() {
            //Initialize all hardware through method
            TestBot.init(hardwareMap);

        }

        public void loop() {
            // Get Joystick values
            float leftValue = gamepad1.left_stick_y;
            float rightValue = gamepad1.right_stick_y;

            //Set power of Drive Motors
            TestBot.FrontRight.setPower(rightValue);
            TestBot.BackRight.setPower(rightValue);
            TestBot.FrontLeft.setPower(leftValue);
            TestBot.BackLeft.setPower(leftValue);

            //Set Power of Peripherals
            if(gamepad1.a) {
                if(motorPowerA != 1.0f) {
                    motorPowerA = motorPowerA + INCREMENT;
                    TestBot.MotorA.setPower(motorPowerA);
                }
                motorPowerA = 0.0f;
            } else if (gamepad1.x){
                if(motorPowerA != 0.0f) {
                    motorPowerA = motorPowerA - INCREMENT;
                    TestBot.MotorA.setPower(motorPowerA);
                }
                motorPowerA = 1.0f;
            }
            if(gamepad1.y) {
                TestBot.MotorB.setPower(1.0);
            } else if (gamepad1.b){
                TestBot.MotorB.setPower(-1.0);
            } else {
                TestBot.MotorB.setPower(0.0);
            }

            if(gamepad1.left_trigger > 0.0) {
                TestBot.leftPusher.setPosition(0.0);
            } else {
                TestBot.leftPusher.setPosition(1.0);
            }

            if(gamepad1.right_trigger > 0.0) {
                TestBot.rightPusher.setPosition(1.0);
            } else {
                TestBot.rightPusher.setPosition(0.0);
            }
            if (gamepad1.left_bumper){
                TestBot.leftClaw.setPosition(0.0);
            } else {
                TestBot.rightClaw.setPosition(0.5);
            }
            if (gamepad1.right_bumper){
                TestBot.rightClaw.setPosition(0.0);
            } else {
                TestBot.rightClaw.setPosition(0.5);
            }

            colorS.enableLed(false);
            colorNumber = colorS.getColorNumber();

            telemetry.addData("Color Number", colorNumber);
            telemetry.addData("Red:", colorS.colorDecisionRed());
            telemetry.addData("Blue:", colorS.colorDecisionBlue());
            telemetry.addData("LeftODS",(int)TestBot.lineLeft.getRawLightDetected());
            telemetry.addData("RightODS",(int)TestBot.lineRight.getRawLightDetected());
        }

}