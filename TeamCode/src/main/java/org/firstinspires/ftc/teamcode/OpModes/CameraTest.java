package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by abhin on 9/30/2016.
 */
@Autonomous(name = "CameraTest", group = "Testing")
public class CameraTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //Creates Localizer Object to set Parameters
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
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

        waitForStart();

        beacons.activate();

        while (opModeIsActive()){
            for(int i = 0; i < beacons.size(); i++){
                VuforiaTrackable beac = beacons.get(i);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-X Translation",translation.get(1));
                    telemetry.addData(beac.getName() + "-Y Translation",translation.get(2));

                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));
                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);
                }
            }
            telemetry.update();
            //idle();
        }
    }
}
