package org.firstinspires.ftc.teamcode.Calibration;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Libs.LineFollow;
import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/9/2016.
 */
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
                while (gamepad1.a){
                }
            }
            if(gamepad1.b){
                telemetry.addData("Status:","Updating Black Level");
                lineF.setLowValue(bot.lineRight.getRawLightDetected());
                while (gamepad1.b){
                }
            }
        }
    }
}
