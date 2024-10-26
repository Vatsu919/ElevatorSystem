package org.example.strategy;

import org.example.entities.elevator.ElevatorManager;

public interface ElevatorFindingStrategy {
    public int findElevator(int totalFloors, int floor, ElevatorManager elevatorManager);
}
