package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by abhin on 9/28/2016.
 */
public class HardwareBot {

    //Define motors
    public DcMotor backLeft, backRight, frontLeft, frontRight, catapult, sweeper;

    //Define Servos
    public Servo leftPusher, rightPusher;

    //Define Sensors
    public ColorSensor beaconLeft, beaconRight,lineLeft, lineRight;

    //Create a HardwareMap object
    HardwareMap lhmap;

    public HardwareBot() {

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
        beaconLeft = lhmap.colorSensor.get("BeaconLeft");
        beaconRight = lhmap.colorSensor.get("BeaconRight");

        //Set Direction (Might be the other side base// d on orientation)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        catapult.setDirection(DcMotor.Direction.FORWARD);
        sweeper.setDirection(DcMotor.Direction.FORWARD);
        leftPusher.setDirection(Servo.Direction.FORWARD);
        rightPusher.setDirection(Servo.Direction.FORWARD);

        //Initialize Power
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        catapult.setPower(0);
        sweeper.setPower(0);

        // Define and initialize ALL installed servos.
        leftPusher.setPosition(0.0);
        rightPusher.setPosition(0.0);

        //Set Run Mode (Change for Encoder Function)
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        catapult.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
