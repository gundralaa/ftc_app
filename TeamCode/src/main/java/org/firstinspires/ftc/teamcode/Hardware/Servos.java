package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Naisan on 4/16/2016.
 */
public class Servos {
    public Servo beaconRight,
                 beaconLeft;

        public Servos(Servo beaconRight, Servo beaconLeft) {
            this.beaconRight = beaconRight;
            this.beaconLeft = beaconLeft;
        }
}
