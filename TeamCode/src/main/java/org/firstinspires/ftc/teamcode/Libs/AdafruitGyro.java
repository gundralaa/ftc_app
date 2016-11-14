package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.hardware.adafruit.BNO055IMU;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
/**
 * Created by abhin on 11/3/2016.
 */
public class AdafruitGyro {
    /**
     * The Adafruit BNO055IMU is a fusion based Gyro Sensor
     * This library is gonna be used to get angle differences but all calls have already been made
     * The Sensor can give
     *      |Orientation Data|
     *      Acceleration Integration
     *          Position Delta
     *          Velocity Delta
     */
    HardwareBot bot = new HardwareBot();
    public AdafruitGyro(HardwareBot bot) {this.bot = bot;}
    Position P = new Position();
    Velocity V = new Velocity();
    Orientation angles;

    public float getPitch(){
        float pitch;
        angles = bot.imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAngleUnit(AngleUnit.DEGREES);
        pitch = angles.thirdAngle;
        return pitch;
    }
    public float getRoll(){
        float roll;
        angles = bot.imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAngleUnit(AngleUnit.DEGREES);
        roll = angles.secondAngle;
        return roll;
    }
    public float getHeading(){
        float heading;
        angles = bot.imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAngleUnit(AngleUnit.DEGREES);
        heading = angles.firstAngle;
        return heading;
    }




}
