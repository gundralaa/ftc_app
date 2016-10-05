package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareTestBot {

    //Define all motors set to null
    public DcMotor FrontRight, FrontLeft, BackRight, BackLeft, MotorA, MotorB;

    //Define local hmap
    HardwareMap lhmap;


    public HardwareTestBot() {

    }

    public void init(HardwareMap hmap){

        //Reference the local version of hardware map to argument
        lhmap = hmap;

        //Define Motors
        FrontRight = lhmap.dcMotor.get("FrontRight");
        FrontLeft = lhmap.dcMotor.get("FrontLeft");
        BackLeft = lhmap.dcMotor.get("BackLeft");
        BackRight = lhmap.dcMotor.get("BackRight");

        //Set Direction (Might be the other side based on orientation)
        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.FORWARD);


        //Initialize Power
        BackRight.setPower(0);
        BackLeft.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);

        //Set Run Mode (Change for Encoder Fucntion)
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }



}
