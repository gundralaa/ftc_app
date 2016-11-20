package org.firstinspires.ftc.teamcode.Calibration;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libs.LineFollow;
import org.firstinspires.ftc.teamcode.Libs.HardwareBot;

/**
 * Created by abhin on 11/9/2016.
 */
@TeleOp(name = "BlackWhiteCalib",group = "Calibration")
public class BlackWhiteCalibration extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot bot = new HardwareBot();
        LineFollow lineF = new LineFollow(bot);
        telemetry.addData("Status:","Wait for start");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addLine("A for White Level, B for Black Level: Right Sensor");
            if(gamepad1.a){
                telemetry.addData("Status:","Updating White Level");
                lineF.setHighValue(bot.lineRight.getRawLightDetected());
                telemetry.addData("High Value:",lineF.highValue);
                while (gamepad1.a){
                }
            }
            if(gamepad1.b){
                telemetry.addData("Status:","Updating Black Level");
                lineF.setLowValue(bot.lineRight.getRawLightDetected());
                telemetry.addData("Low Value",lineF.lowValue);
                while (gamepad1.b){
                }
            }
            telemetry.update();
            idle();
        }
    }
}
