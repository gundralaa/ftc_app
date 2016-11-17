package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by abhin on 11/16/2016.
 */
public class StraightShooter extends LinearOpMode {
    HardwareBot bot = new HardwareBot();

    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);

        bot.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
