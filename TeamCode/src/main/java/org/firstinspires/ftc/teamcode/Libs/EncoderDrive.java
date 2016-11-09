package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.HardwareTestBot;

/**
 * Created by abhin on 10/31/2016.
 */
public class EncoderDrive {
    HardwareTestBot bot = new HardwareTestBot();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // Andymark Encoder CPR
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * 3.1415);
    public EncoderDrive(HardwareTestBot bot){ this.bot = bot; }

    public void encoderDrive(double rightInches, double leftInches, double speed){
        int newLeftTarget;
        int newRightTarget;

        newLeftTarget = bot.BackLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRightTarget = bot.BackRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

        bot.FrontLeft.setTargetPosition(newLeftTarget);
        bot.BackLeft.setTargetPosition(newLeftTarget);
        bot.FrontRight.setTargetPosition(newRightTarget);
        bot.BackRight.setTargetPosition(newRightTarget);

        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bot.FrontLeft.setPower(Math.abs(speed));
        bot.BackLeft.setPower(Math.abs(speed));
        bot.FrontRight.setPower(Math.abs(speed));
        bot.BackRight.setPower(Math.abs(speed));

        while (bot.BackRight.isBusy() && bot.BackLeft.isBusy()){
        }

        bot.FrontLeft.setPower(0.0);
        bot.BackLeft.setPower(0.0);
        bot.FrontRight.setPower(0.0);
        bot.BackRight.setPower(0.0);

        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
