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
    double initialTranslationY;
    /*
        Wheels: 0
        Tools: 1
        Lego: 2
        Gears: 3
    */
    public CameraFunction(HardwareBot bot){ this.bot = bot;}
    public double getAngle(int i){
        double degrees;
        double angle = 0;
        double direction = 1;
        VuforiaTrackable targ = beacons.get(i) ;
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) targ.getListener()).getPose();

        if(pose != null){
            VectorF translation = pose.getTranslation();
            //VectorF 1 is x translation and 2 is y translation
            //x is side to side with respect to the picture and y is forward and back
            degrees = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));
            if (degrees > 0){
                direction = 1;
            }
            if(degrees < 0){
                direction = -1;
            }
            angle = direction*(180 - Math.abs(degrees));
        }
        return angle;
    }
    public void setInitialY(int i){
        VuforiaTrackable targ = beacons.get(i) ;
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) targ.getListener()).getPose();

        if(pose != null){
            VectorF translation = pose.getTranslation();
            initialTranslationY = translation.get(2);
        }

    }
    public double diffTranslationY(int i){
        double difference = 0;
        double current;
        VuforiaTrackable targ = beacons.get(i) ;
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) targ.getListener()).getPose();

        if(pose != null){
            VectorF translation = pose.getTranslation();
            current = translation.get(2);
            //If the robot is moving backwards from the target then (-)
            difference = current - initialTranslationY;
        }
        return difference;
    }
    public boolean getIsVisible(int i){
        boolean isVisible = false;
        VuforiaTrackable targ = beacons.get(i);
        isVisible = ((VuforiaTrackableDefaultListener) targ.getListener()).isVisible();
        return isVisible;
    }


}
