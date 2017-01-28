package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;

/**
 * Created by abhin on 11/8/2016.
 */
@Autonomous(name = "RedAuton",group = "Competition")
public class AutonomousRed extends LinearOpMode {

    HardwareBot bot = new HardwareBot();
    ColorSensing colorS = new ColorSensing(bot,this);
    LineFollow follow = new LineFollow(bot,this);
    EncoderDrive drive = new EncoderDrive(bot,this);
    DistanceSensing distS = new DistanceSensing(bot,this);

    double whiteLineRaw;//Find this
    double blackRaw;//Find this
    double toLineIn; //in inches also find
    double toLineMm; //
    int speed = 500; //counts per second
    boolean teamColor;

    @Override
    public void runOpMode() throws InterruptedException {

        bot.init(hardwareMap);
        idle();

        telemetry.addData("Task: ", "Initializing");
        telemetry.update();

        bot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        bot.BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        idle();

        telemetry.addData(">", "Gyro Calibrating. Do Not move!");
        telemetry.update();
        bot.gyroSensor.calibrate();

        // make sure the gyro is calibrated.
        while (bot.gyroSensor.isCalibrating())  {
            Thread.sleep(50);
            idle();
        }

        telemetry.addData(">","Gyro Calibrated.  Press Start.");
        telemetry.update();


        telemetry.addData("Task: ", "Waiting For Start");
        telemetry.update();

        waitForStart();


        //sleep(3000); //Initial Sleep

        drive.encoderDrive(15,15,0.2);

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


        drive.encoderDrive(-2,-2,0.2);
        sleep(500);

        drive.turnCClock(0.5);
        //int targetdistance = drive.pivotTurn(60,18);
        //while(bot.BackLeft.getCurrentPosition() < targetdistance)
        sleep(1000);
        drive.stop();
        sleep(500);

        follow.driveTillLine(-0.5,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        //TODO First turn towards first beacon
        follow.turnCCLine(0.5,0.4);

        distS.driveTillDist(-0.2,15.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionRed(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.rightPusher.setPosition(1.0); // Left if Blue on sensor
        } else {
            bot.leftPusher.setPosition(0.0); //Right if not Blue on sensor
        }
        sleep(1000); // Pause

        drive.encoderDrive(4,4,0.2); //Drive really close to the beacon

        sleep(500);

        bot.leftPusher.setPosition(1.0); // Reset Pushers
        bot.rightPusher.setPosition(0.0); // Reset Pushers

        //distS.driveAwayDist(0.3,31.0); // Drive away from the beacon

        drive.encoderDrive(-15, -15, 0.4);
        // Turn around 90 to the next line
        drive.turnCClock(0.4);
        sleep(1300);
        drive.stop();

        drive.encoderDrive(10,10,0.4);

        follow.driveTillLine(-0.4,0.4); // Drive to the Line
        sleep(500); //Pause

        follow.driveTillLine(0.2,0.5); // Back Up and Line Up Precisely
        sleep(500);

        //TODO First turn towards second beacon
        follow.turnCCLine(0.4,0.4);

        sleep(1000);

        distS.driveTillDist(-0.3,15.0); //Drive till certain distance from beacon

        sleep(500); // Pause

        teamColor = colorS.colorDecisionRed(); // Decide the color

        sleep(500); // Pause

        if (teamColor){
            bot.rightPusher.setPosition(1.0); //If true then extend with left
        } else {
            bot.leftPusher.setPosition(0.0); //If false then extend with right
        }
        sleep(1000);

        drive.encoderDrive(4,4,0.2); //Get really close to the beacon

        sleep(500);

        drive.encoderDrive(-10,-10,0.2);

        sleep(500);

        bot.leftPusher.setPosition(1.0);
        bot.rightPusher.setPosition(0.0);

    }


}
