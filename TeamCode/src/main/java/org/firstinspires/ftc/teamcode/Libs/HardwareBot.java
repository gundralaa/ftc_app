package org.firstinspires.ftc.teamcode.Libs;
import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.hardware.adafruit.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.adafruit.NaiveAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
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
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Libs.AdvancedAccelerationIntegrator;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareBot {
    private final int COLOR_SENSOR_ADDRESS = 0x3c;
    //Define all motors set to null
    public DcMotor FrontRight, FrontLeft, BackRight, BackLeft, MotorA, MotorB;
    public Servo leftClaw, rightClaw, leftPusher, rightPusher,secondBall,vortexPusher;
    public I2cDevice beaconSensor;
    public I2cDeviceSynch beaconSensorReader;
    public OpticalDistanceSensor lineLeft,lineRight,wallDist;
    public VuforiaLocalizer vuforia;
    public VuforiaTrackables beacons;
    public BNO055IMU imu;
    public ModernRoboticsI2cRangeSensor rangeSensor;

    //Define local hmap
    HardwareMap lhmap;

    public HardwareBot() {

    }

    public void init(HardwareMap hmap){

        //Reference the local version of hardware map to argument
        lhmap = hmap;
        //BNO055IMU
        /*
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        */
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
        secondBall = lhmap.servo.get("secondBall");
        vortexPusher = lhmap.servo.get("vortexPusher");

        beaconSensor = lhmap.i2cDevice.get("BeaconSensor");
        beaconSensorReader = new I2cDeviceSynchImpl(beaconSensor, I2cAddr.create8bit(COLOR_SENSOR_ADDRESS),false);
        beaconSensorReader.engage();

        lineLeft = lhmap.opticalDistanceSensor.get("lineLeft");
        lineRight = lhmap.opticalDistanceSensor.get("lineRight");
        wallDist = lhmap.opticalDistanceSensor.get("wallDist");

        rangeSensor = lhmap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");


        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",

        // and named "imu".
        //imu = lhmap.get(BNO055IMU.class, "imu");
        //imu.initialize(parameters);

        //Set Direction (Might be the other side base// d on orientation)
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.FORWARD);
        MotorA.setDirection(DcMotor.Direction.FORWARD);
        MotorB.setDirection(DcMotor.Direction.FORWARD);
        leftPusher.setDirection(Servo.Direction.FORWARD);
        rightPusher.setDirection(Servo.Direction.FORWARD);
        leftClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setDirection(Servo.Direction.FORWARD);


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

        leftClaw.setPosition(0.0);
        rightClaw.setPosition(1.0);

        secondBall.setPosition(1.0);

        vortexPusher.setPosition(0.4);

        //Set Run Mode (Change for Encoder Function)
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Creates Localizer Parameters Object to set Parameters
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        // Will show the camera on screen leave parameter less if no need.
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        //Which camera we will be using.
        params.vuforiaLicenseKey = "AYjW+kn/////AAAAGckyQkdtk0g+vMt7+v21EQwSR82nxrrI34xlR+F75StLY+q3kjvWvgZiO0rBImulRIdCD4IjzWtqgZ8lPunOWuhUUi5eERTExNybyTwhn4GpdRr2XkcN+5uFD+5ZRoMfgx+z4RL4ONOLGWVMD30/VhwSM5vvkKB9C1VyGK0DyKcidSfxW8yhL1BKR2J0B5DtRtDW91hzalAEH2BfKE2+ee/F8f0HQ67DE5nnoVqrnT+THXWFb9W6OOBLszYdHTkUMtMV5U0RQxNuTBkeYGHtgcy17ULkQLY9Lnv0pqCLKdvlz4P3gtUAHPs/kr1cfzcaCS4iRY+ZlwxxLIKSazd0u4NSBjhH/f+zKJMaL/uVG2j4";
        //Vuforia Key taken from Dev Portal
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        // AR or object that will appear on image target
        //Object used to run Vuphoria by FTC. Pass in param
        vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS,4);

        beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");


    }



}
