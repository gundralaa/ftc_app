package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;


/**
 * Created by abhin on 10/24/2016.
 */
/*
         * We have many options here
         * 1. We can follow the line until a condition is met
         *      a.Distance
         *      b.Camera
         * 2. We can use the camera to line up with the target and drive until
         *      a.Distance
         *      b.Camera
         *      c.Encoders
         *
         *      Beacon Positions
         *      Blue 1 = Wheels
         *      Blue 2 = Legos
         *      Red 1 = Gears
         *      Red 2 = Tools
*/
class AutonomousBlue extends LinearOpMode {

    HardwareBot bot = new HardwareBot();
    ColorSensing colorS = new ColorSensing(bot,this);
    LineFollow follow = new LineFollow(bot,this);
    EncoderDrive drive = new EncoderDrive(bot,this);
    DistanceSensing distS = new DistanceSensing(bot,this);
    CameraFunction cameraF;

    int cameraTarg1 = 0;
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
        cameraF = new CameraFunction(bot);

        telemetry.addData("Task: ", "Initilizing");
        telemetry.update();


        idle();

        bot.BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();

        cameraF.beacons.activate();

        sleep(3000); //Initial Sleep

        drive.straightF(-0.3);
        sleep(500); //TODO Change this value
        drive.stop();

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


        drive.turnClock(0.4); //Turn until image is visible
        boolean initialVisible = cameraF.getIsVisible(cameraTarg1);
        while(opModeIsActive() && !initialVisible){
            initialVisible = cameraF.getIsVisible(cameraTarg1); // Using the Wheels
            idle();
        }


        follow.driveTillLine(-0.5,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        drive.turnClock(0.4); //Start turn
        boolean wheelVisible = cameraF.getIsVisible(cameraTarg1);
        while(opModeIsActive() && !wheelVisible){
            wheelVisible = cameraF.getIsVisible(cameraTarg1);
            idle();
        }
        //Stop the motors
        drive.stop();

        sleep(1000);
        drive.turnClock(0.3);

        double wheelAngle = cameraF.getAngle(cameraTarg1);
        while(opModeIsActive() &&  wheelAngle < 180 && wheelAngle > 0 ){
            wheelAngle = cameraF.getAngle(cameraTarg1);
            idle();
        }

        drive.stop();


        distS.driveTillDist(-0.2,15.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionBlue(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.leftPusher.setPosition(0.0); // Left if Blue on sensor
        } else {
            bot.rightPusher.setPosition(1.0); //Right if not Blue on sensor
        }
        sleep(1000); // Pause

        drive.straightF(-0.4);
        sleep(300);
        drive.stop();

        sleep(500);

        bot.leftPusher.setPosition(1.0); // Reset Pushers
        bot.rightPusher.setPosition(0.0); // Reset Pushers

        distS.driveAwayDist(0.3,31.0); // Drive away from the beacon

        // Turn around 90 to the next line
        drive.turnCClock(0.4);
        sleep(1000);
        drive.stop();

        follow.driveTillLine(-0.5,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        drive.turnClock(0.4); //Start turn
        boolean legosVisible = cameraF.getIsVisible(cameraTarg2);
        while(opModeIsActive() && !legosVisible){
            legosVisible = cameraF.getIsVisible(cameraTarg2);
            idle();
        }
        //Stop the motors
        drive.stop();

        sleep(1000);
        drive.turnClock(0.3);

        double legosAngle = cameraF.getAngle(cameraTarg2);
        while(opModeIsActive() &&  legosAngle < 180 && legosAngle > 0 ){
            legosAngle = cameraF.getAngle(cameraTarg2);
            idle();
        }

        drive.stop();

        distS.driveTillDist(-0.3,15.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionBlue(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.leftPusher.setPosition(0.0);
        } else {
            bot.rightPusher.setPosition(1.0);
        }
        sleep(1000);

        drive.straightF(-0.4);
        sleep(300);
        drive.stop();

        sleep(500);

        bot.leftPusher.setPosition(1.0);
        bot.rightPusher.setPosition(0.0);

        drive.straightF(0.4);
        sleep(300);
        drive.stop();

    }

}
