package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Libs.AdafruitGyro;
import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;

/**
 * Created by abhin on 11/19/2016.
 */
@Autonomous(name = "TestingLinear",group = "Testing")
public class LinearTestingOpMode extends LinearOpMode {
    HardwareBot bot = new HardwareBot();

    LineFollow follow = new LineFollow(bot,this);
    DistanceSensing distS =  new DistanceSensing(bot,this);
    AdafruitGyro adaGyro = new AdafruitGyro(bot);
    ColorSensing colorS = new ColorSensing(bot,this);
    CameraFunction cameraF;
    EncoderDrive drive =  new EncoderDrive(bot,this);

    int imageTarget = 1;
    double wheelAngle;
    boolean wheelVisible;
    double legoAngle;
    boolean legoVisible;


    @Override
    public void runOpMode() throws InterruptedException {
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

        sleep(1000);

        drive.encoderDrive(-26,-26,0.65); //Optimal 0.65 Power
        sleep(500);
        //drive.pivotTurn(90,0.5,18);

        while (opModeIsActive()){
            wheelAngle = cameraF.getAngle(0);
            wheelVisible = cameraF.getIsVisible(0);
            legoAngle = cameraF.getAngle(2);
            legoVisible = cameraF.getIsVisible(2);

            telemetry.addData("Wheels Angle", wheelAngle);
            telemetry.addData("Wheel Visibility", wheelVisible);
            telemetry.addData("Lego Angle", legoAngle);
            telemetry.addData("Lego Visible", legoVisible);
            telemetry.update();
        }



    }
}
