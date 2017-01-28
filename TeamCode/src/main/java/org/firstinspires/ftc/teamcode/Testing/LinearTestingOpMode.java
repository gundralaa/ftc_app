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
import org.firstinspires.ftc.teamcode.Libs.MRGyro;

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
    MRGyro gyroMethods = new MRGyro(bot,this);

    int imageTarget = 1;
    double wheelAngle;
    boolean wheelVisible;
    double legoAngle;
    boolean legoVisible;
    int heading;
    int angleZ;


    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);

        telemetry.addData("Task: ", "Initilizing");
        telemetry.update();

        bot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
/*
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        idle();
*/
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

        sleep(1000);

        drive.encoderDrive(-26,-26,0.25); //Forward
        sleep(500);
        drive.encoderDrive(26,26,0.25); //Backward
        sleep(500);

        gyroMethods.turnCCAngle(90,0.3);
        sleep(500);
        gyroMethods.turnCAngle(90,0.3);


        while (opModeIsActive()){

            heading = bot.gyroSensor.getHeading();
            angleZ = bot.gyroSensor.getIntegratedZValue();

            telemetry.addData("Heading: ", heading);
            telemetry.addData("AngleZ: ", angleZ);
            telemetry.update();


        }



    }
}
