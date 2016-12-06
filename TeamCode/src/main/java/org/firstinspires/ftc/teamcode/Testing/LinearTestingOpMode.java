package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    CameraFunction camerF = new CameraFunction(bot);
    EncoderDrive drive =  new EncoderDrive(bot,this);

    int imageTarget = 1;


    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);


    }
}
