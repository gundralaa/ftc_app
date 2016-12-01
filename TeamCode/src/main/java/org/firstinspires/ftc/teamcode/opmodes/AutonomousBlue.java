package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.methods.DrivingMethods;
import org.firstinspires.ftc.teamcode.methods.SensorMethods;


/**
 * Created by naisan on 11/16/2016.
 */

@Autonomous(name = "BlueAuton", group = "Autonomous")

public class AutonomousBlue extends LinearOpMode {

    HardwareBot bot = new HardwareBot();

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);

        idle();

        bot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Wait for Start");
        waitForStart();

        double previousDist = bot.rangeSensor.cmUltrasonic();

        //TODO: Measure distances for launching
        SensorMethods.distanceWithEncoders(bot, 10, 0.2);
        telemetry.addData("Status","Distance Moved");

        //Shoot balls
        bot.motorB.setPower(1);
        sleep(1000);
        bot.motorB.setPower(0);

        //Turn so the robot can proceed to the white line by the beacon
        DrivingMethods.turnClockwise(bot, 0.5);
        //TODO: Either use gyro/encoders or test to find the correct amount of time to turn
        sleep(1000);
        DrivingMethods.setAllMotors(bot, 0);
        telemetry.addData("Status","Turned");

        //TODO: Measure the light reflectance of the white line
        SensorMethods.stopAtColoredLine(bot, 1);

        //TODO: Measure the light reflectance and the distance
        SensorMethods.followColoredLine(bot, 1, 1);

        //TODO: Push one of the buttons
        boolean beaconBlue = SensorMethods.beaconBlue(bot);


    }

}
