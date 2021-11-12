package com.group9.cleansweep.controlsystem;

import com.group9.cleansweep.controlsystem.ObstacleDetection;

public class BackSensor {

    public ObstacleDetection.ObstacleType run() {
        ObstacleDetection obstacle = new ObstacleDetection();
        return obstacle.isObstacleDetected;
    }
}
