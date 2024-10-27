package org.example.strategy;

import org.example.entities.elevator.ElevatorManager;

public class SlotBasedElevatorFindingStrategy implements ElevatorFindingStrategy{
    @Override
    public int findElevator(int totalFloors, int floor, ElevatorManager elevatorManager) {
        int totalElevators = elevatorManager.getElevatorCount();
        int slotSize = totalFloors / totalElevators;

        for(int i = 0; i < totalElevators; i++) {
            if(floor >= i * slotSize && floor < (i + 1) * slotSize) {
                return i;
            }
        }
        return -1;
    }
}
