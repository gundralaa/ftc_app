package org.firstinspires.ftc.teamcode.Libs;

/**
 * Created by abhin on 11/12/2016.
 */
import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import static org.firstinspires.ftc.robotcore.external.navigation.NavUtil.*;

/**
 *
 */
public class AdvancedAccelerationIntegrator implements BNO055IMU.AccelerationIntegrator
{
    //------------------------------------------------------------------------------------------
    // State
    //------------------------------------------------------------------------------------------

    BNO055IMU.Parameters parameters;
    Position position;
    Velocity velocity;
    Acceleration acceleration;

    public Position getPosition() { return this.position; }
    public Velocity getVelocity() { return this.velocity; }
    public Acceleration getAcceleration() { return this.acceleration; }

    //------------------------------------------------------------------------------------------
    // Construction
    //------------------------------------------------------------------------------------------

    public AdvancedAccelerationIntegrator()
    {
        this.parameters = null;
        this.position = null;
        this.velocity = null;
        this.acceleration = null;
    }

    //------------------------------------------------------------------------------------------
    // Operations
    //------------------------------------------------------------------------------------------

    @Override public void initialize(BNO055IMU.Parameters parameters, Position initialPosition, Velocity initialVelocity)
    {
        this.parameters = parameters;
        this.position = initialPosition;
        this.velocity = initialVelocity;
        this.acceleration = null;
    }

    @Override public void update(Acceleration linearAcceleration)
    {
        // We should always be given a timestamp here
        if (linearAcceleration.acquisitionTime != 0)
        {
            // We can only integrate if we have a previous acceleration to baseline from
            if (acceleration != null)
            {
                Acceleration accelPrev    = acceleration;
                Velocity     velocityPrev = velocity;

                acceleration = linearAcceleration;

                if (accelPrev.acquisitionTime != 0)
                {
                    Velocity deltaVelocity = meanIntegrate(acceleration, accelPrev);
                    velocity = plus(velocity, deltaVelocity);
                }

                if (velocityPrev.acquisitionTime != 0)
                {
                    Position deltaPosition = meanIntegrate(velocity, velocityPrev);
                    position = plus(position, deltaPosition);
                }

                if (parameters.loggingEnabled)
                {
                    RobotLog.vv(parameters.loggingTag, "dt=%.3fs accel=%s vel=%s pos=%s", (acceleration.acquisitionTime - accelPrev.acquisitionTime)*1e-9, acceleration, velocity, position);
                }
            }
            else
                acceleration = linearAcceleration;
        }
    }
}
