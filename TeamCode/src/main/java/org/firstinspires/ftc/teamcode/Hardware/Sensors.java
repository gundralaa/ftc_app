package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Naisan on 4/16/2016.
 */
public class Sensors {
     public ColorSensor colorSensorLeft,
                        colorSensorRight;

        public Sensors(ColorSensor colorSensorLeft, ColorSensor colorSensorRight) {
            this.colorSensorLeft = colorSensorLeft;
            this.colorSensorRight = colorSensorRight;
        }
}
