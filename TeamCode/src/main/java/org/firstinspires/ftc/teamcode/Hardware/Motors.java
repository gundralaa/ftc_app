package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Naisan on 4/16/2016.
 */
public class Motors {
    public DcMotor backLeft,
                   backRight,
                   frontLeft,
                   frontRight,
                   catapult,
                   sweeper;

        public Motors(DcMotor backLeft, DcMotor backRight, DcMotor frontLeft, DcMotor frontRight, DcMotor catapult, DcMotor sweeper) {
            this.backLeft = backLeft;
            this.backRight = backRight;
            this.frontLeft = frontLeft;
            this.frontRight = frontRight;
            this.catapult = catapult;
            this.sweeper = sweeper;
        }
    }

