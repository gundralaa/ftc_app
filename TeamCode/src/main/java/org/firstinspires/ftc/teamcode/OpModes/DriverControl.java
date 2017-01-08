package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Methods.DrivingMethods;

/**
 * Created by abhin on 9/28/2016.
 */
@TeleOp(name = "DriverControl", group = "Competition")
public class DriverControl extends LinearOpMode {

    final float INCREMENT = 0.1f;

    float motorPowerA = 0.0f;
    float motorPowerB = 0.0f;

    double wheelAngle;
    boolean wheelVisible;

    double legoAngle;
    boolean legoVisible;

    //Create Hardware Object
    HardwareBot bot = new HardwareBot();
    DrivingMethods drive = new DrivingMethods(bot);

    CameraFunction cameraF;

    int colorNumber;
    double values [] = new double[2];

    public void runOpMode() throws InterruptedException {
        //Initialize all hardware through method
        bot.init(hardwareMap);
        idle();
        cameraF = new CameraFunction(bot);
        idle();
        telemetry.addData("Task: ", "Initilizing");
        telemetry.update();

        bot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        idle();

        cameraF.beacons.activate();
        idle();

        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            //Get Joystick Values
            float leftValue = gamepad1.right_stick_y;
            float rightValue = gamepad1.left_stick_y;

            values = drive.square(leftValue,rightValue);

            //Set power of Drive Motors
            drive.tankDrive(values[0],values[1]);

            //Set Power of Peripherals
            if(gamepad2.left_bumper) {
                bot.leftClaw.setPosition(1.0);
                bot.rightClaw.setPosition(0.0);
            } else{
                bot.leftClaw.setPosition(0.0);
                bot.rightClaw.setPosition(1.0);
            }
            if(gamepad2.y) {
                bot.MotorB.setPower(1.0);
            } else {
                bot.MotorB.setPower(0.0);
            }

            if(gamepad2.a) {
                bot.leftPusher.setPosition(0.0);
            } else {
                bot.leftPusher.setPosition(1.0);
            }

            if(gamepad2.b) {
                bot.rightPusher.setPosition(1.0);
            } else {
                bot.rightPusher.setPosition(0.0);
            }
            if(gamepad1.right_bumper){
                bot.vortexPusher.setPosition(1.0);
            } else if(gamepad1.b){
                bot.vortexPusher.setPosition(0.0);
            } else {
                bot.vortexPusher.setPosition(0.4);
            }
        }
    }
}
