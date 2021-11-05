package com.group9.cleansweep.controlsystem;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Stack;

public class Navigation {
	// this is where the stack/queue would be for tiles that have been visited
	// need a method that returns boolean for is cleaning done
	Stack<Tile> visited;
	Tile currentPos = new Tile();
	FloorPlan floorPlan;

	Map<String, Tile> floorPlanMap;

	public Navigation(FloorPlan floorPlan) {
		this.visited = new Stack<>();
		this.floorPlan = floorPlan;
		this.floorPlanMap = floorPlan.getFloorPlanMap();


		// Assuming charging station is start
		// set current position to the charging station

		for(Map.Entry<String, Tile> entry : floorPlanMap.entrySet()){
			if(entry.getValue().isChargingStation()) {
				currentPos = entry.getValue();
				currentPos.setRightNext(currentPos.getRightNext());
				currentPos.setLeftNext(currentPos.getLeftNext());
				currentPos.setTopNext(currentPos.getTopNext());
				currentPos.setBottomNext((currentPos.getBottomNext()));
			}
		}
	}


	public Tile traverseRight(Tile target) {
		currentPos.setRightNext(target);
		currentPos.setLeftNext(currentPos);
		currentPos.setTopNext(target.getTopNext());
		currentPos.setBottomNext(target.getBottomNext());
		target.setVisited(true);
		System.out.println("Traversed right from tile " + currentPos.getLeftNext().getId() + " to tile " + currentPos.getId() + ".");
		return target;
	}

	public Tile traverseLeft(Tile target) {
		currentPos.setRightNext(currentPos);
		currentPos.setLeftNext(target);
		currentPos.setTopNext(target.getTopNext());
		currentPos.setBottomNext(target.getBottomNext());
		target.setVisited(true);
		System.out.println("Traversed left from tile " + currentPos.getRightNext().getId() + " to tile " + currentPos.getId() + ".");
		return target;
	}

	public Tile traverseTop(Tile target) {
		currentPos.setRightNext(target.getRightNext());
		currentPos.setLeftNext(target.getLeftNext());
		currentPos.setTopNext(target);
		currentPos.setBottomNext(currentPos);
		target.setVisited(true);
		System.out.println("Traversed up from tile " + currentPos.getBottomNext().getId() + " to tile " + currentPos.getId() + ".");
		return target;
	}

	public Tile traverseBottom(Tile target){
		currentPos.setRightNext(target.getRightNext());
		currentPos.setLeftNext(target.getLeftNext());
		currentPos.setTopNext(currentPos);
		currentPos.setBottomNext(target);
		target.setVisited(true);
		System.out.println("Traversed down from tile " + currentPos.getTopNext().getId() + " to tile " + currentPos.getId() + ".");
		return target;
	}

	public Boolean isObstacleRight(Tile currentPos) {
		if(currentPos.getRightNext().getObstacle()) {
			System.out.println("Detected tile " + currentPos.getRightNext().getId() + " as obstacle to the right.");
			return true;
		} else return false;
	}

	public Boolean isObstacleLeft(Tile currentPos) {
		if(currentPos.getLeftNext().getObstacle()) {
			System.out.println("Detected tile " + currentPos.getLeftNext().getId() + " as obstacle to the left.");
			return true;
		} else return false;
	}

	public Boolean isObstacleTop(Tile currentPos) {
		if(currentPos.getTopNext().getObstacle()) {
			System.out.println("Detected tile " + currentPos.getTopNext().getId() + " as obstacle above.");
			return true;
		} else return false;
	}

	public Boolean isObstacleBottom(Tile currentPos) {
		if(currentPos.getBottomNext().getObstacle()) {
			System.out.println("Detected tile " + currentPos.getBottomNext().getId() + " as obstacle below.");
			return true;
		} else return false;
	}



    // TODO: go back to power station
	//  keep iterating back through stack, traversing back to charging station
	// Still need to figure out algorithm for it
	private void backToChargingStation() {

	}



//	@Getter
//	@Setter
//	private boolean isVisited = false;

	@Getter
	@Setter
	private boolean isTrackingCycleComplete = false;

	// vv Done vv

	//print moved up one cell
	//print moved left one cell
	//print attempting to move up one cell
	//print obstacle detected one cell up
	//print attempting to move left one cell
	//print moved left one cell
	//return left Tile object
}
