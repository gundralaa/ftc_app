package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Libs.LineFollow;
import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;

/**
 * Created by abhin on 11/1/2016.
 */
@Autonomous(name = "TestingAuton",group = "Testing")
public class LinearTestingOpMode extends LinearOpMode {

    HardwareBot testBot = new HardwareBot();
    EncoderDrive Drive = new EncoderDrive(testBot);
    ColorSensing ColorS = new ColorSensing(testBot);
    LineFollow follow =  new LineFollow(testBot);

    int colorNumber;
    double WHITE_THRESHOLD = 0.4;
    double DISTANCE_FROM_BEACON = 12.0;

    @Override
    public void runOpMode() throws InterruptedException {

        testBot.init(hardwareMap);

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
        testBot.BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testBot.BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testBot.FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testBot.FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

        sleep(3000); //Sleep Method

        testBot.BackLeft.setPower(-0.5);
        testBot.BackRight.setPower(-0.5);
        testBot.FrontLeft.setPower(-0.5);
        testBot.FrontRight.setPower(-0.5);

        // Run until forward the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.lineRight.getLightDetected() < WHITE_THRESHOLD)) {

            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  testBot.lineRight.getLightDetected());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(500);

        testBot.BackLeft.setPower(0.2);
        testBot.BackRight.setPower(0.2);
        testBot.FrontLeft.setPower(0.2);
        testBot.FrontRight.setPower(0.2);

        // Run back until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.lineRight.getLightDetected() < WHITE_THRESHOLD + 0.1)) {

            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  testBot.lineRight.getLightDetected());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(500);
        /*
        testBot.BackLeft.setPower(0.4);
        testBot.BackRight.setPower(-0.4);
        testBot.FrontLeft.setPower(0.4);
        testBot.FrontRight.setPower(-0.4);

        double previousDist = testBot.rangeSensor.cmUltrasonic();
        double currentDist = previousDist;

        // run until the white line is seen OR the driver presses STOP;
        while ((opModeIsActive() && (previousDist >= currentDist + 0.5)) || (opModeIsActive() && (previousDist >= currentDist - 0.5))) {
            previousDist = currentDist; //Set last current distance to previous
            currentDist = testBot.rangeSensor.cmUltrasonic(); //Set new current
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);
        */
        //ClockWiseTurn
        testBot.BackLeft.setPower(0.4);
        testBot.BackRight.setPower(-0.4);
        testBot.FrontLeft.setPower(0.4);
        testBot.FrontRight.setPower(-0.4);
        sleep(700);
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        testBot.BackLeft.setPower(-0.3);
        testBot.BackRight.setPower(-0.3);
        testBot.FrontLeft.setPower(-0.3);
        testBot.FrontRight.setPower(-0.3);

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.rangeSensor.cmUltrasonic() > DISTANCE_FROM_BEACON)) {

            // Display the light level while we are looking for the line
            telemetry.addData("distance in cm",  testBot.rangeSensor.cmUltrasonic());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(1000);

        if(ColorS.colorDecisionBlue()){
            testBot.leftPusher.setPosition(0.0);
        }
        else {
            testBot.rightPusher.setPosition(1.0);
        }

        sleep(500);
        testBot.leftPusher.setPosition(1.0);
        testBot.rightPusher.setPosition(0.0);

        testBot.BackLeft.setPower(0.3);
        testBot.BackRight.setPower(0.3);
        testBot.FrontLeft.setPower(0.3);
        testBot.FrontRight.setPower(0.3);

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.rangeSensor.cmUltrasonic() < 31.0)) {

            // Display the light level while we are looking for the line
            telemetry.addData("distance in cm",  testBot.rangeSensor.cmUltrasonic());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        testBot.BackLeft.setPower(0.0);
        testBot.BackRight.setPower(0.0);
        testBot.FrontLeft.setPower(0.0);
        testBot.FrontRight.setPower(0.0);

        testBot.BackLeft.setPower(-0.4);
        testBot.BackRight.setPower(0.4);
        testBot.FrontLeft.setPower(-0.4);
        testBot.FrontRight.setPower(0.4);
        sleep(1000);
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        testBot.BackLeft.setPower(-0.5);
        testBot.BackRight.setPower(-0.5);
        testBot.FrontLeft.setPower(-0.5);
        testBot.FrontRight.setPower(-0.5);

        // Run until forward the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.lineRight.getLightDetected() < WHITE_THRESHOLD)) {

            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  testBot.lineRight.getLightDetected());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(500);

        testBot.BackLeft.setPower(0.2);
        testBot.BackRight.setPower(0.2);
        testBot.FrontLeft.setPower(0.2);
        testBot.FrontRight.setPower(0.2);

        // Run back until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.lineRight.getLightDetected() < WHITE_THRESHOLD + 0.1)) {

            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  testBot.lineRight.getLightDetected());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        testBot.BackLeft.setPower(0.4);
        testBot.BackRight.setPower(-0.4);
        testBot.FrontLeft.setPower(0.4);
        testBot.FrontRight.setPower(-0.4);
        sleep(1000);
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(500);

        testBot.BackLeft.setPower(-0.3);
        testBot.BackRight.setPower(-0.3);
        testBot.FrontLeft.setPower(-0.3);
        testBot.FrontRight.setPower(-0.3);

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (testBot.rangeSensor.cmUltrasonic() > DISTANCE_FROM_BEACON)) {

            // Display the light level while we are looking for the line
            telemetry.addData("distance in cm",  testBot.rangeSensor.cmUltrasonic());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

        // Stop all motors
        testBot.BackLeft.setPower(0);
        testBot.BackRight.setPower(0);
        testBot.FrontLeft.setPower(0);
        testBot.FrontRight.setPower(0);

        sleep(1000);

        if(ColorS.colorDecisionBlue()){
            testBot.leftPusher.setPosition(0.0);
        }
        else {
            testBot.rightPusher.setPosition(1.0);
        }

        sleep(500);
        testBot.leftPusher.setPosition(1.0);
        testBot.rightPusher.setPosition(0.0);

    }
}
