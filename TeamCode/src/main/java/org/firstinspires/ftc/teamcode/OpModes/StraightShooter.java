package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by abhin on 11/16/2016.
 */
@Autonomous(name = "Shooting", group = "Competition")
public class StraightShooter extends LinearOpMode {
    HardwareBot bot = new HardwareBot();

    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);

        telemetry.addData("Task: ", "Resetting Encoders");
        telemetry.update();
        /*
        testBot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        */
        idle();
        /*
        testBot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

        sleep(3000);


        bot.BackLeft.setPower(-0.2);
        bot.BackRight.setPower(-0.2);
        bot.FrontLeft.setPower(-0.2);
        bot.FrontRight.setPower(-0.2);
        sleep(200);
        bot.BackLeft.setPower(0);
        bot.BackRight.setPower(0);
        bot.FrontLeft.setPower(0);
        bot.FrontRight.setPower(0);
        sleep(500);
        bot.secondBall.setPosition(1.0);
        sleep(500);

        bot.MotorB.setPower(1.0);
        sleep(500);
        bot.MotorB.setPower(0.0);
        sleep(1000);

        bot.secondBall.setPosition(0.3);
        sleep(1000);
        bot.secondBall.setPosition(1.0);
        sleep(500);

        bot.MotorB.setPower(1.0);
        sleep(1000);
        bot.MotorB.setPower(0.0);
        sleep(1000);

    }
}
