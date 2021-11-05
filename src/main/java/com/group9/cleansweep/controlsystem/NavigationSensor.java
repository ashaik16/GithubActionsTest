package com.group9.cleansweep.controlsystem;

import static com.group9.cleansweep.controlsystem.ObstacleDetection.ObstacleType.OBSTACLE;
import static com.group9.cleansweep.controlsystem.ObstacleDetection.ObstacleType.OPEN;
import static com.group9.cleansweep.controlsystem.ObstacleDetection.ObstacleType.STAIRS;
import static com.group9.cleansweep.controlsystem.ObstacleDetection.ObstacleType.UNKNOWN;

public class NavigationSensor {

    private FrontSensor frontSensor;
    private LeftSensor leftSensor;
    private RightSensor rightSensor;
    private BackSensor backSensor;
    private UnderSensor underSensor;

    public void CheckSensors() {
        //Make sensorNum into a String
        String sensor = CheckFrontSensor();
        if (sensor == "Front")
            //proceed forward
            return;
        else if (sensor == "Left")
            //proceed left
            return;
        else if (sensor == "Right")
            //proceed right
            return;
        else if (sensor == "Back")
            //proceed backwards
            return;
        else if (sensor == "Under")
            //Not sure what we would do here honestly, lol
            return;
        else if (sensor == "UNKNOWN")
            //Stop cleaning
            return;

    }

    public String CheckFrontSensor() {
        String sensor = "Front";
        frontSensor.getClass();

        frontSensor.run();
        if(frontSensor.equals(OPEN))
            return sensor;
        else {
            sensor = "Left";
            return CheckLeftSensor(sensor);
        }
    }
    public String CheckLeftSensor(String sensor){
        leftSensor.run();
        if(leftSensor.equals(OPEN))
            return sensor;
        else {
            sensor = "Right";
            return CheckRightSensor(sensor);
        }
    }
    public String CheckRightSensor(String sensor) {
        rightSensor.run();
        if(rightSensor.equals(OPEN))
            return sensor;
        else {
            sensor = "Back";
            return CheckBackSensor(sensor);
        }
    }
    public String CheckBackSensor(String sensor) {
        backSensor.run();
        if(rightSensor.equals(OPEN))
            return sensor;
        else {
            sensor = "Under";
            return CheckUnderSensor(sensor);
        }
    }
    public String CheckUnderSensor(String sensor) {
        underSensor.run();
        if(underSensor.equals(OPEN))
            return sensor;
        else if(underSensor.equals(STAIRS)) {
            //STOP CLEANING***
            System.out.println("stairs detected. Stopping...");
            return sensor;
        }
        else {
            sensor = "UNKNOWN";
            System.out.println("Unknown obstacle detected.  Stopping...");
            return sensor;
        }
    }

}