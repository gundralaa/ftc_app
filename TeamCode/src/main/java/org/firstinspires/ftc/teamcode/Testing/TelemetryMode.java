package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Libs.AdafruitGyro;
import org.firstinspires.ftc.teamcode.Libs.CameraFunction;
import org.firstinspires.ftc.teamcode.Libs.ColorSensing;
import org.firstinspires.ftc.teamcode.Libs.DistanceSensing;
import org.firstinspires.ftc.teamcode.Libs.EncoderDrive;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;
import org.firstinspires.ftc.teamcode.Libs.LineFollow;
import org.firstinspires.ftc.teamcode.R;

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
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters();
        // Will show the camera on screen leave parameter less if no need.
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        //Which camera we will be using.
        params.vuforiaLicenseKey = "AYjW+kn/////AAAAGckyQkdtk0g+vMt7+v21EQwSR82nxrrI34xlR+F75StLY+q3kjvWvgZiO0rBImulRIdCD4IjzWtqgZ8lPunOWuhUUi5eERTExNybyTwhn4GpdRr2XkcN+5uFD+5ZRoMfgx+z4RL4ONOLGWVMD30/VhwSM5vvkKB9C1VyGK0DyKcidSfxW8yhL1BKR2J0B5DtRtDW91hzalAEH2BfKE2+ee/F8f0HQ67DE5nnoVqrnT+THXWFb9W6OOBLszYdHTkUMtMV5U0RQxNuTBkeYGHtgcy17ULkQLY9Lnv0pqCLKdvlz4P3gtUAHPs/kr1cfzcaCS4iRY+ZlwxxLIKSazd0u4NSBjhH/f+zKJMaL/uVG2j4";
        //Vuphoria Key taken from Dev Portal
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        // AR or object that will appear on image target
        //Object used to run Vuphoria by FTC. Pass in param
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS,4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");
        idle();
        telemetry.addData("Status","Waiting for Start");
        telemetry.update();
        waitForStart();
        beacons.activate();

        while (opModeIsActive()){
            //telemetry.addData("Gyro: Pitch",adaGyro.getPitch());
            //telemetry.addData("Gyro: Roll", adaGyro.getPitch());
            //telemetry.addData("Gyro: Heading", adaGyro.getHeading());
            //telemetry.addData("Color: Number", colorS.getColorNumber());
            VuforiaTrackable beac = beacons.get(imageTarget);
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
            telemetry.addData(beac.getName() + "-IsVisible",((VuforiaTrackableDefaultListener) beac.getListener()).isVisible());
            if(pose != null){
                VectorF translation = pose.getTranslation();

                telemetry.addData(beac.getName() + "-X Translation",translation.get(1));
                telemetry.addData(beac.getName() + "-Y Translation",translation.get(2));

                double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));
                telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);
            }
            telemetry.update();
            //telemetry.addData("Distance:", bot.rangeSensor.getDistance(DistanceUnit.CM));
            idle();
        }
    }
}
