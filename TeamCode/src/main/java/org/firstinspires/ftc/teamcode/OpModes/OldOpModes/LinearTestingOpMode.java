package org.firstinspires.ftc.teamcode.OpModes.OldOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.HardwareTestBot;

/**
 * Created by abhin on 11/1/2016.
 */
public class LinearTestingOpMode extends LinearOpMode {

    HardwareTestBot testBot = new HardwareTestBot();

    @Override
    public void runOpMode() throws InterruptedException {

        testBot.init(hardwareMap);

        telemetry.addData("Task: ", "Resetting Encoders");
        telemetry.update();

        testBot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        testBot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();





    }
}
