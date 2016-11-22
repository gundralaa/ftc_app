package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Libs.AdafruitGyro;
import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;

/**
 * Created by abhin on 11/21/2016.
 */
@Autonomous(name = "Telemetry Testing",group = "Testing")
public class TelemetryMode extends LinearOpMode {
    HardwareBot bot = new HardwareBot();

    LineFollow follow = new LineFollow(bot,this);
    DistanceSensing distS =  new DistanceSensing(bot,this);
    AdafruitGyro adaGyro = new AdafruitGyro(bot);
    ColorSensing colorS = new ColorSensing(bot,this);
    CameraFunction camerF = new CameraFunction(bot);

    int imageTarget = 1;

    /**
     * Image Target Values
     * Wheels: 0 Tools: 1 Lego: 2 Gears: 3
     * @throws InterruptedException
     */

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        telemetry.addData("Status","Waiting for Start");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Gyro: Pitch",adaGyro.getPitch());
            telemetry.addData("Gyro: Roll", adaGyro.getPitch());
            telemetry.addData("Gyro: Heading", adaGyro.getHeading());
            telemetry.addData("Color: Number", colorS.getColorNumber());
            telemetry.addData("Camera: Is Visible", camerF.getIsVisible(imageTarget));
            telemetry.addData("Camera: Angle",camerF.getAngle(imageTarget));
            telemetry.addData("Distance:", bot.rangeSensor.getDistance(DistanceUnit.CM));
            idle();
        }
    }
}
