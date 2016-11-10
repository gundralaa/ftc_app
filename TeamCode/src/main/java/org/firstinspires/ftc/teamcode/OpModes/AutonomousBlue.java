package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;


/**
 * Created by abhin on 10/24/2016.
 */
class AutonomousBlue extends LinearOpMode {

    HardwareBot bot = new HardwareBot();
    ColorSensing colorS = new ColorSensing(bot);
    LineFollow follow = new LineFollow(bot);
    EncoderDrive drive = new EncoderDrive(bot);
    DistanceSensing distS = new DistanceSensing(bot);
    double whiteLineRaw;//Find this
    double blackRaw;//Find this
    double toLine; //in inches also find

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

        follow.driveTillLine(-0.5);
        sleep(500);
        follow.simpleFollow(1, distS.getDistance() > toLine, -0.3);
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
        follow.simpleFollow(1, distS.getDistance() > toLine, -0.3);
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
