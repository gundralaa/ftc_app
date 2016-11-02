package org.firstinspires.ftc.teamcode.Libs;

import org.firstinspires.ftc.teamcode.Hardware.HardwareTestBot;

/**
 * Created by abhin on 11/1/2016.
 */
public class ColorSensing {
    HardwareTestBot bot = new HardwareTestBot();

    private final int COLOR_NUMBER_ADDRESS = 0x04;
    private final int LED_ENABLE = 0x00;
    private final int LED_DISABLE = 0x01;
    private final int COMMAND_ADDRESS = 0x03;


    public boolean colorDecisionRed(boolean ledEnable){
        boolean decision =  false;
        int colorNumber;
        byte [] sensorCache;

        if(ledEnable){
            bot.beaconSensorReader.write8(COMMAND_ADDRESS,LED_ENABLE);
        }
        else {
            bot.beaconSensorReader.write8(COMMAND_ADDRESS, LED_ENABLE);
        }
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

    public boolean colorDecisionBlue(boolean ledEnable){
        boolean decision =  false;
        int colorNumber;
        byte [] sensorCache;

        if(ledEnable){
            bot.beaconSensorReader.write8(COMMAND_ADDRESS,LED_ENABLE);
        }
        else {
            bot.beaconSensorReader.write8(COMMAND_ADDRESS, LED_ENABLE);
        }
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

    public int getColorNumber (boolean ledEnable){
        byte [] sensorCache;
        if(ledEnable){
            bot.beaconSensorReader.write8(COMMAND_ADDRESS,LED_ENABLE);
        }
        else {
            bot.beaconSensorReader.write8(COMMAND_ADDRESS, LED_ENABLE);
        }
        sensorCache = bot.beaconSensorReader.read(COLOR_NUMBER_ADDRESS,1);
        return (sensorCache[0] & 0xff);
    }

}
