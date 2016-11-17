package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
/*
 * Created by abhin on 9/28/2016.
 */
@TeleOp(name = "DriverControl", group = "Competition")
public class DriverControl extends OpMode {
        final float INCREMENT = 0.1f;

        HardwareBot bot = new HardwareBot();

        public void init() {
            bot.init(hardwareMap);
        }

        public void loop() {
            // Get Joystick values
            float leftValue = gamepad1.left_stick_y;
            float rightValue = gamepad1.right_stick_y;

            //Set power of Drive Motors
            bot.frontRight.setPower(rightValue);
            bot.backRight.setPower(rightValue);
            bot.frontLeft.setPower(leftValue);
            bot.backLeft.setPower(leftValue);

            //Set Power of Peripherals
            if(gamepad1.a) {
                if(bot.motorA.getPower() != 1) {
                    bot.motorA.setPower(bot.motorA.getPower() + INCREMENT);
                }
            } else if (gamepad1.x) {
                if(bot.motorA.getPower() != 0) {
                    bot.motorA.setPower(bot.motorA.getPower() - INCREMENT);
                }
            }

            if(gamepad1.y) {
                bot.motorB.setPower(1.0);
            } else if (gamepad1.b){
                bot.motorB.setPower(-1.0);
            } else {
                bot.motorB.setPower(0.0);
            }

            if(gamepad1.left_trigger > 0.0) {
                bot.leftPusher.setPosition(0.0);
            } else {
                bot.leftPusher.setPosition(1.0);
            }

            if(gamepad1.right_trigger > 0.0) {
                bot.rightPusher.setPosition(1.0);
            } else {
                bot.rightPusher.setPosition(0.0);
            }
            if (gamepad1.left_bumper){
                bot.leftClaw.setPosition(0.0);
            } else {
                bot.rightClaw.setPosition(0.5);
            }
            if (gamepad1.right_bumper){
                bot.rightClaw.setPosition(0.0);
            } else {
                bot.rightClaw.setPosition(0.5);
            }
        }
}
