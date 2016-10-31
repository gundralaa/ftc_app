package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by abhin on 9/28/2016.
 */
@TeleOp(name = "TestBotOpMode", group = "Testing")

public class TestingOpMode extends OpMode {
        final float INCREMENT = 0.1f;
        float motorPowerA = 0.0f;
        float motorPowerB = 0.0f;
        //Create Hardware Object
        HardwareTestBot TestBot = new HardwareTestBot();



        public void init() {
            //Initialize all hardware through method
            TestBot.init(hardwareMap);


        }

        public void loop() {
            // Get Joystick values
            float leftValue = -gamepad1.left_stick_y;
            float rightValue = -gamepad1.right_stick_y;

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
            }
            else if (gamepad1.x){
                if(motorPowerA != 0.0f) {
                    motorPowerA = motorPowerA - INCREMENT;
                    TestBot.MotorA.setPower(motorPowerA);
                }
                motorPowerA = 1.0f;
            }
            if(gamepad1.y) {
                if(motorPowerB != 1.0f) {
                    motorPowerB = motorPowerB + INCREMENT;
                    TestBot.MotorB.setPower(motorPowerB);
                }
                motorPowerB = 0.0f;
            }
            else if (gamepad1.b){
                if(motorPowerB != 0.0f) {
                    motorPowerB = motorPowerB - INCREMENT;
                    TestBot.MotorB.setPower(motorPowerB);
                }
                motorPowerA = 1.0f;
            }

            if(gamepad1.left_trigger > 0.0) {
                TestBot.leftPusher.setPosition(0.0);
            }
            else {
                TestBot.leftPusher.setPosition(0.5);
            }

            if(gamepad1.right_bumper) {
                TestBot.rightPusher.setPosition(1.0);
            }
            else {
                TestBot.rightPusher.setPosition(0.5);
            }
            telemetry.addData("Clear", TestBot.beaconSensor.alpha());
            telemetry.addData("Red  ", TestBot.beaconSensor.red());
            telemetry.addData("Green", TestBot.beaconSensor.green());
            telemetry.addData("Blue ", TestBot.beaconSensor.blue());

        }

}
