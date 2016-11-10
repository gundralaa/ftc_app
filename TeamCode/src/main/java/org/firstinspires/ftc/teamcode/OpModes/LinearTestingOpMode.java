package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;

/**
 * Created by abhin on 11/1/2016.
 */

public class LinearTestingOpMode extends LinearOpMode {

    HardwareBot testBot = new HardwareBot();
    EncoderDrive Drive = new EncoderDrive(testBot);
    ColorSensing ColorS = new ColorSensing(testBot);

    int colorNumber;

    @Override
    public void runOpMode() throws InterruptedException {

        testBot.init(hardwareMap);

        telemetry.addData("Task: ", "Resetting Encoders");
        telemetry.update();

        testBot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        idle();

        testBot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

         colorNumber = ColorS.getColorNumber();

        //Drive.encoderDrive(3,3,0.5);
        //sleep(3000);
        //Drive.encoderDrive(4,4,0.5);





    }
}
