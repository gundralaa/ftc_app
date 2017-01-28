package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by abhin on 1/27/2017.
 */
public class MRGyro {
    LinearOpMode opMode;
    HardwareBot bot;
    int angleZ;
    EncoderDrive drive = new EncoderDrive(bot,opMode);

    public MRGyro(HardwareBot bot, LinearOpMode opMode){
        this.opMode = opMode;
        this.bot = bot;
    }

    public void turnCCAngle(int angle, double power){
        bot.gyroSensor.resetZAxisIntegrator();
        angleZ = bot.gyroSensor.getIntegratedZValue();
        drive.turnCClock(power);
        while (angleZ < angle){
            opMode.telemetry.addData("Angle", angleZ);
        }
        drive.stop();
    }

    public void turnCAngle(int angle, double power){
        bot.gyroSensor.resetZAxisIntegrator();
        angleZ = -(bot.gyroSensor.getIntegratedZValue());
        drive.turnClock(power);
        while (angleZ < -(angle)){
            opMode.telemetry.addData("Angle", angleZ);
        }
        drive.stop();
    }
}
