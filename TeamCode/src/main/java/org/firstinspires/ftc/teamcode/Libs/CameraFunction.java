package org.firstinspires.ftc.teamcode.Libs;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/9/2016.
 */
public class CameraFunction {
    HardwareBot bot = new HardwareBot();
    VuforiaTrackables beacons = bot.beacons;

    public double getAngle(String target){
        int i = 0;
        double angle = 0;
        switch (target){
            case "Wheels":
                i = 0;
                break;
            case "Tools":
                i = 1;
                break;
            case "Lego":
                i = 2;
                break;
            case "Gears":
                i=3;
                break;
        }
        VuforiaTrackable targ = beacons.get(i);
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) targ.getListener()).getPose();

        if(pose != null){
            VectorF translation = pose.getTranslation();
            //VectorF 1 is x translation and 2 is y translation
            //Refer to positioning diagram
            angle = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));
        }
        return angle;

    }

}
