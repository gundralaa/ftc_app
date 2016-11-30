package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Harry and Steven on 11/14/2016.
 */
@TeleOp(name = "singleMotor", group = "Test")
public class singleMotor extends OpMode {


    DcMotor left;

    final double OPEN = 1.0;
    final double CLOSED = 0;
    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("M_drive_BL");


    }
    @Override
    public void loop() {


        if (gamepad1.a){
            left.setPower(1.0);
        }


        else if (gamepad1.b){

            left.setPower(-1.0);
        }
        else if (gamepad1.x){
            left.setPower(0.0);
        }



    }}