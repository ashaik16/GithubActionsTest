package com.group9.cleansweep.controlsystem;

public class CleanSweep {

	/*
	 * floor plan to get all tiles or get start tile Navigation object move to next
	 * tile - return new tile Dirt Detection object is capacity full? >=4 if full ->
	 * emptyDirtTank clean dirt stuff
	 * 
	 * Power Management object
	 * 
	 */

	public CleanSweep(){


	}

	public void doWork() {
		FloorPlan floorPlan = new FloorPlan();
		floorPlan.buildGenericFloorPlan();
		DirtDetection dirtDetection = new DirtDetection();
		dirtDetectionProcess(floorPlan);
		floorPlan.writeFloorPlanToFile();
	}

	public void doWorkFromFile(String fileLocation){
		FloorPlan floorPlan = new FloorPlan();
		floorPlan.convertFileToFloorplan("src/main/java/com/group9/cleansweep/controlsystem/FloorPlanFile/SampleFloor.json");
		DirtDetection dirtDetection = new DirtDetection();
		dirtDetectionProcess(floorPlan);

	}

	public void dirtDetectionProcess(FloorPlan floorPlan) {
		DirtDetection dirtDetection = new DirtDetection();
		dirtDetection.dirtDetectionProcess(floorPlan);
	}
}