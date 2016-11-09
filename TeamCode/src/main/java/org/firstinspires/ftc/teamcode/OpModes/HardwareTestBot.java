package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareTestBot {
    private final int COLOR_SENSOR_ADDRESS = 0x3c;
    //Define all motors set to null
    public DcMotor FrontRight, FrontLeft, BackRight, BackLeft, MotorA, MotorB;
    public Servo leftClaw, rightClaw, leftPusher, rightPusher;
    public I2cDevice beaconSensor;
    public I2cDeviceSynch beaconSensorReader;
    public OpticalDistanceSensor lineLeft,lineRight,wallDist;

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

        MotorA = lhmap.dcMotor.get("MotorA");
        MotorB = lhmap.dcMotor.get("MotorB");

        leftPusher = lhmap.servo.get("leftPusher");
        rightPusher = lhmap.servo.get("rightPusher");
        leftClaw = lhmap.servo.get("leftClaw");
        rightClaw = lhmap.servo.get("rightClaw");

        beaconSensor = lhmap.i2cDevice.get("BeaconSensor");
        beaconSensorReader = new I2cDeviceSynchImpl(beaconSensor, I2cAddr.create8bit(COLOR_SENSOR_ADDRESS),false);
        beaconSensorReader.engage();

        lineLeft = lhmap.opticalDistanceSensor.get("lineLeft");
        lineRight = lhmap.opticalDistanceSensor.get("lineRight");
        wallDist = lhmap.opticalDistanceSensor.get("wallDist");

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
        leftPusher.setPosition(1.0);
        rightPusher.setPosition(0.0);

        //Set Run Mode (Change for Encoder Function)
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }



}
