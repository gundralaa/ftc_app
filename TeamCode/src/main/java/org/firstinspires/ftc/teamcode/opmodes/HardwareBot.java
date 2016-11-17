package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareBot {
    private final int COLOR_SENSOR_ADDRESS = 0x3c;
    //Define all motors set to null
    public DcMotor frontRight, frontLeft, backRight, backLeft, motorA, motorB;
    public Servo leftClaw, rightClaw, leftPusher, rightPusher;
    public I2cDevice beaconSensor;
    public I2cDeviceSynch beaconSensorReader;
    public OpticalDistanceSensor lineFollowing, wallDist;
    public ModernRoboticsI2cRangeSensor rangeSensor;

    //Define local hmap
    HardwareMap lhmap;

    public HardwareBot() {

    }

    public void init(HardwareMap hmap){

        lhmap = hmap;

        //Define Motors

        frontLeft = lhmap.dcMotor.get("frontLeft");
        backLeft = lhmap.dcMotor.get("backLeft");

        motorA = lhmap.dcMotor.get("motorA");
        motorB = lhmap.dcMotor.get("motorB");

        leftPusher = lhmap.servo.get("leftPusher");
        rightPusher = lhmap.servo.get("rightPusher");
        leftClaw = lhmap.servo.get("leftClaw");
        rightClaw = lhmap.servo.get("rightClaw");

        beaconSensor = lhmap.i2cDevice.get("BeaconSensor");
        beaconSensorReader = new I2cDeviceSynchImpl(beaconSensor, I2cAddr.create8bit(COLOR_SENSOR_ADDRESS),false);
        beaconSensorReader.engage();

        lineFollowing = lhmap.opticalDistanceSensor.get("lineFollowing");
        wallDist = lhmap.opticalDistanceSensor.get("wallDist");

        rangeSensor = lhmap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");

        //Set Direction (Might be the other side base// d on orientation)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        motorA.setDirection(DcMotor.Direction.FORWARD);
        motorA.setDirection(DcMotor.Direction.FORWARD);
        motorA.setDirection(DcMotor.Direction.FORWARD);
        motorB.setDirection(DcMotor.Direction.FORWARD);
        leftPusher.setDirection(Servo.Direction.FORWARD);
        rightPusher.setDirection(Servo.Direction.FORWARD);
        leftClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setDirection(Servo.Direction.FORWARD);


        //Initialize Power
        motorA.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        motorA.setPower(0);
        motorA.setPower(0);
        motorB.setPower(0);

        // Define and initialize ALL installed servos.
        leftPusher.setPosition(1.0);
        rightPusher.setPosition(0.0);

        leftClaw.setPosition(0.0);
        rightClaw.setPosition(0.0);

        //Set Run Mode (Change for Encoder Function)
        motorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters();
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AYjW+kn/////AAAAGckyQkdtk0g+vMt7+v21EQwSR82nxrrI34xlR+F75StLY+q3kjvWvgZiO0rBImulRIdCD4IjzWtqgZ8lPunOWuhUUi5eERTExNybyTwhn4GpdRr2XkcN+5uFD+5ZRoMfgx+z4RL4ONOLGWVMD30/VhwSM5vvkKB9C1VyGK0DyKcidSfxW8yhL1BKR2J0B5DtRtDW91hzalAEH2BfKE2+ee/F8f0HQ67DE5nnoVqrnT+THXWFb9W6OOBLszYdHTkUMtMV5U0RQxNuTBkeYGHtgcy17ULkQLY9Lnv0pqCLKdvlz4P3gtUAHPs/kr1cfzcaCS4iRY+ZlwxxLIKSazd0u4NSBjhH/f+zKJMaL/uVG2j4";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS,4);
    }



}
