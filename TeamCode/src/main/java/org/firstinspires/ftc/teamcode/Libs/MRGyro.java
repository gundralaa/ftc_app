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
    public void piCCGyroTurn(int angle, int gain){
        int currentAngle = bot.gyroSensor.getIntegratedZValue();
        int targetAngle = currentAngle + Math.abs(angle)
        int error = targetAngle - currentAngle;
        int power;
        while(opmode.opModeIsActive() && error > 0){
            power = (error * PCONSTANT) + gain;
            bot.FrontLeft.setPower(power);
            bot.BackLeft.setPower(power);
            bot.FrontRight.setPower(-power);
            bot.BackRight.setPower(-power);
            currentAngle = bot.gyroSensor.getIntegratedZValue();
            error = targetAngle - currentAngle;
        }
        bot.FrontLeft.setPower(0.0);
        bot.BackLeft.setPower(0.0);
        bot.FrontRight.setPower(0.0);
        bot.BackRight.setPower(0.0);
    }
    public void piCGyroTurn(int angle, int gain){
        int currentAngle = bot.gyroSensor.getIntegratedZValue();
        int targetAngle = currentAngle - Math.abs(angle)
        int error = -(targetAngle - currentAngle);
        int power;
        while(opmode.opModeIsActive() && error > 0){
            power = (error * PCONSTANT) + gain;
            bot.FrontLeft.setPower(-power);
            bot.BackLeft.setPower(-power);
            bot.FrontRight.setPower(power);
            bot.BackRight.setPower(power);
            currentAngle = bot.gyroSensor.getIntegratedZValue();
            error = -(targetAngle - currentAngle);
        }
        bot.FrontLeft.setPower(0.0);
        bot.BackLeft.setPower(0.0);
        bot.FrontRight.setPower(0.0);
        bot.BackRight.setPower(0.0);
    }
}
