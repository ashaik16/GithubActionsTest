package com.group9.cleansweep.controlsystem;

import java.text.DecimalFormat;
import java.util.Map.Entry;

import com.group9.cleansweep.Enum.UnitConsumedEnum;

import lombok.Getter;
import lombok.Setter;
import com.group9.cleansweep.FloorPlan;
import com.group9.cleansweep.Tile;
public class PowerManagement {

	@Getter

	private final int totalBatteryUnit = 250;
	@Getter
	@Setter
	private double currentUnitOfCharge = 0;

	@Getter
	@Setter
	private UnitConsumedEnum unitConsumedEnum;

	@Getter
	@Setter
	private FloorPlan floorPlanType;

	@Getter
	@Setter
	private boolean isMimumumPowerCapacityReached=false;


	private final double minimumCapacityForPowerUnit = 10.0;
	  private static final DecimalFormat df = new DecimalFormat("0.00");
	public boolean powerManagementProcess(Entry<String, Tile> previousTile, Entry<String, Tile> currentTile, Entry<String, Tile> tile, int dirtAmount) {

	String previousSurfaceType = "";
		String currentSurfaceType = "";
		double unitOfCharge = 0.0;
		

			currentSurfaceType = currentTile.getValue().getSurfaceType();
			if (previousTile != null && !(currentSurfaceType.equals(previousTile.getValue().getSurfaceType()))) {

				previousSurfaceType = previousTile.getValue().getSurfaceType();
				unitOfCharge = getAverageUnitOfCharge(currentSurfaceType, previousSurfaceType);
				
			} else 
				unitOfCharge = getUnitOfCharge(currentSurfaceType);

	
			currentUnitOfCharge = currentUnitOfCharge + unitOfCharge+dirtAmount;

			return checkIfMinimumPowerCapacityReached(currentUnitOfCharge);

		}


	public float getUnitOfCharge(String floorPlanType) {

		float unitOfCharge = unitConsumedEnum.valueOf(floorPlanType).getUnitsConsumedPerFloorType();
	//	System.out.println("UnitOfCharge: " + unitOfCharge);
		return unitOfCharge;
	}

	public float getAverageUnitOfCharge(String currentFloorPlanType, String previousFloorPlanType) {

		float previousUnitOfCharge = unitConsumedEnum.valueOf(previousFloorPlanType).getUnitsConsumedPerFloorType();

		float currentUnitOfCharge = unitConsumedEnum.valueOf(currentFloorPlanType).getUnitsConsumedPerFloorType();

		float avgUnitOfCharge = (previousUnitOfCharge + currentUnitOfCharge) / 2;

	//	System.out.println("Average Unit Of Charge: " + avgUnitOfCharge);
		return avgUnitOfCharge;

	}

	public boolean checkIfMinimumPowerCapacityReached(double currentPowerUnit) {
		double remainingBattery=totalBatteryUnit-currentPowerUnit;
		String batteryPercent= df.format((remainingBattery/totalBatteryUnit)*100);
		System.out.println("\n Battery Power Remaining:"+batteryPercent+"%");
		if (remainingBattery < minimumCapacityForPowerUnit) {
			isMimumumPowerCapacityReached=true;
			StatusCheck statusCheck = new StatusCheck();
			statusCheck.setStatus("\n BATTERY LOW ! PLEASE RECHARGE THE VACUUM!!!!!");

		}
		return isMimumumPowerCapacityReached;
	}

}
