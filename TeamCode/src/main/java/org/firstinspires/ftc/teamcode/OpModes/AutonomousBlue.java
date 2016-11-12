package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;


/**
 * Created by abhin on 10/24/2016.
 */
@Autonomous(name = "BlueAuton", group = "Autonomous")
class AutonomousBlue extends LinearOpMode {

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

        bot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        idle();

        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();
        drive.setMaxSpeedAll(speed);
        follow.driveTillLine(-0.5);
        sleep(500);
        /*
         * We have many options here
         * 1. We can follow the line until a condition is met
         *      a.Distance
         *      b.Camera
         * 2. We can use the camera to line up with the target and drive until
         *      a.Distance
         *      b.Camera
         *      c.Encoders
         */
        follow.simpleFollow(1, distS.getDistance() > toLineIn, -0.3);
        //follow.simpleFollow(1,cameraF.getTraslationY(cameraTarg1) < toLineMm,-0.3);
        //drive.encoderDrive();
        sleep(500);
        teamColor = colorS.colorDecisionBlue();
        sleep(500);

        if (teamColor){
            bot.leftPusher.setPosition(0.0);
        } else {
            bot.rightPusher.setPosition(1.0);
        }
        sleep(1000);

        bot.leftPusher.setPosition(1.0);
        bot.rightPusher.setPosition(0.0);

        drive.encoderDrive(3.0,3.0,0.5);//Calculate this
        sleep(500);
        drive.pivotTurn(90,0.5,18);//Calculate this

        follow.driveTillLine(-0.5);
        sleep(500);
        follow.simpleFollow(1, distS.getDistance() > toLineIn, -0.3);
        //drive.encoderDrive();
        sleep(500);
        teamColor = colorS.colorDecisionBlue();
        sleep(500);

        if (teamColor){
            bot.leftPusher.setPosition(0.0);
        } else {
            bot.rightPusher.setPosition(1.0);
        }
        sleep(1000);

        bot.leftPusher.setPosition(1.0);
        bot.rightPusher.setPosition(1.0);

    }

}
