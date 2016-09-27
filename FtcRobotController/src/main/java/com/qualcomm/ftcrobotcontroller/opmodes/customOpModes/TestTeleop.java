package com.qualcomm.ftcrobotcontroller.opmodes.customOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by naisan on 9/27/16.
 */
public class TestTeleop extends OpMode {
    DcMotor FrontRight,
            FrontLeft,
            BackRight,
            BackLeft,
            MotorA,
            MotorB;

    public void init() {
        FrontRight = hardwareMap.dcMotor.get("FrontRight");
        FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
        BackRight = hardwareMap.dcMotor.get("BackRight");
        BackLeft = hardwareMap.dcMotor.get("BackLeft");
        MotorA = hardwareMap.dcMotor.get("MotorA");
        MotorB = hardwareMap.dcMotor.get("MotorB");
    }

    public void loop() {

        double xValue = gamepad1.left_stick_x;
        double yValue = gamepad1.left_stick_y;

        FrontRight.setPower(xValue);
        BackRight.setPower(xValue);

        FrontLeft.setPower(yValue);
        BackLeft.setPower(yValue);

        if(gamepad1.a) {
            MotorA.setPower(1);
        }

        if(gamepad1.b) {
            MotorB.setPower(1);
        }
   }
}

