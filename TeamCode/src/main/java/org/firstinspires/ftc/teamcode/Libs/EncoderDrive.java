package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by abhin on 10/31/2016.
 */
public class EncoderDrive {
    HardwareBot bot;
    LinearOpMode opMode;
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // Andymark Encoder CPR
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * 3.1415);
    public EncoderDrive(HardwareBot bot, LinearOpMode opMode){
        this.bot = bot;
        this.opMode = opMode;
    }

    public void encoderDrive(double rightInches, double leftInches, double power){
        int newBackLeftTarget;
        int newBackRightTarget;
        int newFrontLeftTarget;
        int newFrontRightTarget;

        int directionRight;
        int directionLeft;

        newBackLeftTarget = bot.BackLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newBackRightTarget = bot.BackRight.getCurrentPosition() - (int)((rightInches) * COUNTS_PER_INCH);
        newFrontLeftTarget = bot.FrontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newFrontRightTarget = bot.FrontRight.getCurrentPosition() - (int)((rightInches) * COUNTS_PER_INCH);
/*
        bot.FrontLeft.setTargetPosition(newFrontLeftTarget);
        bot.BackLeft.setTargetPosition(newBackLeftTarget);
        bot.FrontRight.setTargetPosition(newFrontRightTarget);
        bot.BackRight.setTargetPosition(newBackRightTarget);

        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
*/
        if (newFrontRightTarget > bot.FrontRight.getCurrentPosition()){
            directionRight = -1;
        } else if (newFrontRightTarget < bot.FrontRight.getCurrentPosition()){
            directionRight = 1;
        } else {
            directionRight = 1;
        }
        if (newFrontLeftTarget > bot.FrontLeft.getCurrentPosition()){
            directionLeft = 1;
        } else if (newFrontLeftTarget < bot.FrontLeft.getCurrentPosition()){
            directionLeft = -1;
        } else {
            directionLeft = 1;
        }


        if (directionLeft == 1 && directionRight == 1){ // Its Going Forwards
            bot.FrontLeft.setPower((power));
            bot.FrontRight.setPower((power));
            bot.BackLeft.setPower((power));
            bot.BackRight.setPower((power));
            while (opMode.opModeIsActive() && bot.FrontLeft.getCurrentPosition() <= newFrontLeftTarget && bot.FrontRight.getCurrentPosition() >= newFrontRightTarget){
                opMode.telemetry.addData("TargetFrontRight", newFrontRightTarget);
                opMode.telemetry.addData("TargetFrontLeft", newFrontLeftTarget);
                opMode.telemetry.addData("BackRight", bot.BackRight.getCurrentPosition());
                opMode.telemetry.addData("BackLeft", bot.BackLeft.getCurrentPosition());
                opMode.telemetry.addData("FrontRight", bot.FrontRight.getCurrentPosition());
                opMode.telemetry.addData("FrontLeft", bot.FrontLeft.getCurrentPosition());
                opMode.telemetry.update();
            }
            bot.FrontLeft.setPower(0);
            bot.FrontRight.setPower(0);
            bot.BackLeft.setPower(0);
            bot.BackRight.setPower(0);
        }
        if (directionLeft == -1 && directionRight == -1){ // Its Going Backwards
            bot.FrontLeft.setPower(-(power));
            bot.FrontRight.setPower(-(power));
            bot.BackLeft.setPower(-(power));
            bot.BackRight.setPower(-(power));
            while (opMode.opModeIsActive() && bot.FrontLeft.getCurrentPosition() > newFrontLeftTarget && bot.FrontRight.getCurrentPosition() < newFrontRightTarget ){
                opMode.telemetry.addData("TargetFrontRight", newFrontRightTarget);
                opMode.telemetry.addData("TargetFrontLeft", newFrontLeftTarget);
                opMode.telemetry.addData("BackRight", bot.BackRight.getCurrentPosition());
                opMode.telemetry.addData("BackLeft", bot.BackLeft.getCurrentPosition());
                opMode.telemetry.addData("FrontRight", bot.FrontRight.getCurrentPosition());
                opMode.telemetry.addData("FrontLeft", bot.FrontLeft.getCurrentPosition());
                opMode.telemetry.update();
            }
            bot.FrontLeft.setPower(0);
            bot.FrontRight.setPower(0);
            bot.BackLeft.setPower(0);
            bot.BackRight.setPower(0);
        }
        if (directionLeft == 1 && directionRight == -1){
            bot.FrontLeft.setPower((power));
            bot.FrontRight.setPower(-(power));
            bot.BackLeft.setPower((power));
            bot.BackRight.setPower(-(power));
            while (opMode.opModeIsActive() && bot.FrontLeft.getCurrentPosition() < newFrontLeftTarget && bot.FrontRight.getCurrentPosition() < newFrontRightTarget){
                opMode.telemetry.addData("TargetFrontRight", newFrontRightTarget);
                opMode.telemetry.addData("TargetFrontLeft", newFrontLeftTarget);
                opMode.telemetry.addData("BackRight", bot.BackRight.getCurrentPosition());
                opMode.telemetry.addData("BackLeft", bot.BackLeft.getCurrentPosition());
                opMode.telemetry.addData("FrontRight", bot.FrontRight.getCurrentPosition());
                opMode.telemetry.addData("FrontLeft", bot.FrontLeft.getCurrentPosition());
                opMode.telemetry.update();
            }
            bot.FrontLeft.setPower(0);
            bot.FrontRight.setPower(0);
            bot.BackLeft.setPower(0);
            bot.BackRight.setPower(0);
        }
        if (directionLeft == -1 && directionRight == 1){
            bot.FrontLeft.setPower(-(power));
            bot.FrontRight.setPower((power));
            bot.BackLeft.setPower(-(power));
            bot.BackRight.setPower((power));
            while (opMode.opModeIsActive() && bot.FrontLeft.getCurrentPosition() > newFrontLeftTarget && bot.FrontRight.getCurrentPosition() > newFrontRightTarget){
                opMode.telemetry.addData("TargetFrontRight", newFrontRightTarget);
                opMode.telemetry.addData("TargetFrontLeft", newFrontLeftTarget);
                opMode.telemetry.addData("BackRight", bot.BackRight.getCurrentPosition());
                opMode.telemetry.addData("BackLeft", bot.BackLeft.getCurrentPosition());
                opMode.telemetry.addData("FrontRight", bot.FrontRight.getCurrentPosition());
                opMode.telemetry.addData("FrontLeft", bot.FrontLeft.getCurrentPosition());
                opMode.telemetry.update();
            }
            bot.FrontLeft.setPower(0);
            bot.FrontRight.setPower(0);
            bot.BackLeft.setPower(0);
            bot.BackRight.setPower(0);
        }

/*
        while (opMode.opModeIsActive() && bot.BackRight.isBusy() && bot.BackLeft.isBusy() && bot.FrontLeft.isBusy() && bot.FrontRight.isBusy()){
            opMode.telemetry.addData("TargetBackRight", newBackRightTarget);
            opMode.telemetry.addData("TargetFrontLeft", newFrontLeftTarget);
            opMode.telemetry.addData("BackRight", bot.BackRight.getCurrentPosition());
            opMode.telemetry.addData("BackLeft", bot.BackLeft.getCurrentPosition());
            opMode.telemetry.addData("FrontRight", bot.FrontRight.getCurrentPosition());
            opMode.telemetry.addData("FrontLeft", bot.FrontLeft.getCurrentPosition());
            opMode.telemetry.update();
        }
*/
/*
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
*/
    }

    public void pivotTurn(double angle, double power, double width){ // r in inches Positive Angles is Counter Clockwise
        double rightDistance = ((angle/360)*(3.14 * width));
        double leftDistance = (-(angle/360)*(3.14 * width ));

        encoderDrive(rightDistance, leftDistance, power);

    }
    public void setMaxSpeedAll(int speed){
        bot.FrontLeft.setMaxSpeed(speed);
        bot.BackLeft.setMaxSpeed(speed);
        bot.FrontRight.setMaxSpeed(speed);
        bot.BackRight.setMaxSpeed(speed);
    }
    public void straightF(double power){
        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);
    }
    public void straightB(double power){
        bot.FrontLeft.setPower(-power);
        bot.BackLeft.setPower(-power);
        bot.FrontRight.setPower(-power);
        bot.BackRight.setPower(-power);
    }
    public void stop(){
        bot.FrontLeft.setPower(0.0);
        bot.BackLeft.setPower(0.0);
        bot.FrontRight.setPower(0.0);
        bot.BackRight.setPower(0.0);
    }
    public void turnClock(double power){
        bot.FrontLeft.setPower(power);
        bot.BackLeft.setPower(power);
        bot.FrontRight.setPower(-power);
        bot.BackRight.setPower(-power);
    }
    public void turnCClock(double power){
        bot.FrontLeft.setPower(-power);
        bot.BackLeft.setPower(-power);
        bot.FrontRight.setPower(power);
        bot.BackRight.setPower(power);
    }
}
