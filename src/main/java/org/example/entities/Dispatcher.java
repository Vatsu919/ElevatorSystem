package org.example.entities;

import org.example.entities.elevator.ElevatorController;
import org.example.entities.elevator.ElevatorManager;
import org.example.enums.Direction;
import org.example.strategy.ElevatorFindingStrategy;
import org.example.strategy.SlotBasedElevatorFindingStrategy;

public class Dispatcher {
    private final ElevatorFindingStrategy elevatorFindingStrategy;

    private static Dispatcher instance;

    public static Dispatcher getInstance() {
        if(instance==null) {
            synchronized (Dispatcher.class) {
                if(instance==null) {
                    instance = new Dispatcher();
                }
            }
        }
        return instance;
    }
    private Dispatcher() {
        elevatorFindingStrategy = new SlotBasedElevatorFindingStrategy();
    }

    public void submitRequest(int floor, Direction direction, int elevatorId) {
        // Submit request to the elevator manager
        ElevatorController elevatorController = ElevatorManager.getInstance().getElevatorController(elevatorId);
        if(elevatorController != null) {
            elevatorController.submitRequest(floor, direction);
        }
    }

    public void submitRequest(int floor, Direction direction) {
        // Submit request to the elevator manager
        int elevatorId = elevatorFindingStrategy.findElevator(FloorManager.getInstance().getFloorCount(), floor, ElevatorManager.getInstance());
        ElevatorController elevatorController = ElevatorManager.getInstance().getElevatorController(elevatorId);

        if(elevatorController != null) {
            elevatorController.submitRequest(floor, direction);
        }

    }
}
