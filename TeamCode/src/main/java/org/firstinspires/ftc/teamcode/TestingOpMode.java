package org.firstinspires.ftc.teamcode;

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
                TestBot.MotorA.setPower(1.0);
            }
            if(gamepad1.x){
                TestBot.MotorA.setPower(-1.0);
            }
            if (gamepad1.y){
                TestBot.MotorB.setPower(1.0);
            }
            if(gamepad1.b) {
                TestBot.MotorB.setPower(-1.0);
            }
        }

}
