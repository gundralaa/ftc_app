package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;

/**
 * Created by abhin on 11/8/2016.
 */

public class AutonomousRed extends LinearOpMode {

    HardwareBot bot = new HardwareBot();
    ColorSensing colorS = new ColorSensing(bot);
    LineFollow follow = new LineFollow(bot);
    EncoderDrive drive = new EncoderDrive(bot);
    DistanceSensing distS = new DistanceSensing(bot);
    CameraFunction cameraF =  new CameraFunction(bot);
    int cameraTarg1 = 1;
    int cameraTarg2 = 2;
    double whiteLineRaw;//Find this
    double blackRaw;//Find this
    double toLineIn; //in inches also find
    double toLineMm; //
    int speed = 500; //counts per second

    @Override
    public void runOpMode() throws InterruptedException {

        bot.init(hardwareMap);
        boolean teamColor = false;


        telemetry.addData("Task: ", "Resetting Encoders");
        telemetry.update();


        idle();

        bot.BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

        sleep(3000); //Initial Sleep

        follow.driveTillLine(-0.5,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        drive.turnCClock(0.4); //Start turn
        //Initialize Range Variables
        double previousDist = bot.rangeSensor.cmUltrasonic();
        double currentDist = bot.rangeSensor.cmUltrasonic();
        //Turn until the range is starting to increase
        while (opModeIsActive() && (previousDist >= currentDist)) {
            previousDist = currentDist; //Set last current distance to previous
            currentDist = bot.rangeSensor.cmUltrasonic(); //Set new current
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
        //Stop the motors
        drive.stop();

        distS.driveTillDist(-0.3,7.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionRed(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.leftPusher.setPosition(0.0); // Left if Red on sensor
        } else {
            bot.rightPusher.setPosition(1.0); //Right if not Red on sensor
        }
        sleep(1000); // Pause

        bot.leftPusher.setPosition(1.0); // Reset Pushers
        bot.rightPusher.setPosition(0.0); // Reset Pushers

        distS.driveAwayDist(0.4,31.0); // Drive away from the beacon

        // Turn around 90 to the next line
        drive.turnClock(0.4);
        sleep(1000);
        drive.stop();

        follow.driveTillLine(-0.5,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        //Turn Until Lined Up With Wall
        drive.turnCClock(0.4); //Start turn
        //Initialize Range Variables
        double previousDist2 = bot.rangeSensor.cmUltrasonic();
        double currentDist2 = bot.rangeSensor.cmUltrasonic();
        //Turn until the range is starting to increase
        while (opModeIsActive() && (previousDist2 >= currentDist2)) {
            previousDist2 = currentDist2; //Set last current distance to previous
            currentDist2 = bot.rangeSensor.cmUltrasonic(); //Set new current
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
        //Stop the motors
        drive.stop();

        distS.driveTillDist(-0.3,7.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionRed(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.leftPusher.setPosition(0.0);
        } else {
            bot.rightPusher.setPosition(1.0);
        }
        sleep(1000);

        bot.leftPusher.setPosition(1.0);
        bot.rightPusher.setPosition(0.0);

    }


}
