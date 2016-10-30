package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Motors;

/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareTestBot {

    //Define motors

    DcMotor backLeft, backRight, frontLeft, frontRight, catapult, sweeper;

    //Create a Motors object
    Motors motors = new Motors(backLeft, backRight, frontLeft, frontRight, catapult, sweeper);

    //Create a HardwareMap object
    HardwareMap lhmap;


    public HardwareTestBot() {

    }

    public void init(HardwareMap hmap){

        //Reference the local version of hardware map to argument
        lhmap = hmap;

        //Map the motor objects to the hardware map
        frontRight = lhmap.dcMotor.get("FrontRight");
        frontLeft = lhmap.dcMotor.get("FrontLeft");
        backLeft = lhmap.dcMotor.get("BackLeft");
        backRight = lhmap.dcMotor.get("BackRight");

        catapult = lhmap.dcMotor.get("Catapult");
        sweeper = lhmap.dcMotor.get("Sweeper");

        //Map the servo objects to the servo map
        leftPusher = lhmap.servo.get("PusherLeft");
        rightPusher = lhmap.servo.get("PusherRight");

        //Map the sensor objects to the sensor map
        beaconSensor = lhmap.colorSensor.get("BeaconSensor");

        //Set Direction (Might be the other side base// d on orientation)
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.FORWARD);
        MotorA.setDirection(DcMotor.Direction.FORWARD);
        MotorB.setDirection(DcMotor.Direction.FORWARD);
        leftPusher.setDirection(Servo.Direction.FORWARD);
        rightPusher.setDirection(Servo.Direction.FORWARD);

        //Initialize Power
        BackRight.setPower(0);
        BackLeft.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        MotorA.setPower(0);
        MotorB.setPower(0);

        // Define and initialize ALL installed servos.
        leftPusher.setPosition(0.0);
        rightPusher.setPosition(0.0);

        //Set Run Mode (Change for Encoder Function)
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        beaconSensor.enableLed(false);

    }



}
