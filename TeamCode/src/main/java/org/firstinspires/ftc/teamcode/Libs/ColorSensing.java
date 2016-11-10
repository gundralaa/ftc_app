package org.firstinspires.ftc.teamcode.Libs;

import org.firstinspires.ftc.teamcode.OpModes.HardwareBot;

/**
 * Created by abhin on 11/1/2016.
 */
public class ColorSensing {
    HardwareBot bot = new HardwareBot();

    private final int COLOR_NUMBER_ADDRESS = 0x04;
    private final int LED_ENABLE = 0x00;
    private final int LED_DISABLE = 0x01;
    private final int COMMAND_ADDRESS = 0x03;

    public ColorSensing (HardwareBot bot) {
        this.bot = bot;
    }


    public boolean colorDecisionRed(){
        boolean decision =  false;
        int colorNumber;
        byte [] sensorCache;
        sensorCache = bot.beaconSensorReader.read(COLOR_NUMBER_ADDRESS,1);
        colorNumber = (sensorCache[0] & 0xff);
        if(colorNumber == 10 || colorNumber == 11) {
            decision = true;
        }
        else {
            decision = false;
        }
        return decision;
    }

    public boolean colorDecisionBlue(){
        boolean decision =  false;
        int colorNumber;
        byte [] sensorCache;
        sensorCache = bot.beaconSensorReader.read(COLOR_NUMBER_ADDRESS,1);
        colorNumber = (sensorCache[0] & 0xff);
        if(colorNumber == 2 || colorNumber == 3) {
            decision = true;
        }
        else {
            decision = false;
        }
        return decision;
    }

    public int getColorNumber (){
        byte [] sensorCache;
        sensorCache = bot.beaconSensorReader.read(COLOR_NUMBER_ADDRESS,1);
        return (sensorCache[0] & 0xff);
    }

    public void enableLed(boolean ledEnable){
        if(ledEnable){
            bot.beaconSensorReader.write8(COMMAND_ADDRESS,LED_ENABLE);
        }
        else {
            bot.beaconSensorReader.write8(COMMAND_ADDRESS, LED_DISABLE);
        }
    }


}
