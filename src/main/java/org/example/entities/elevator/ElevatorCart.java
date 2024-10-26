package org.example.entities.elevator;

import org.example.entities.displays.ExternalDisplay;
import org.example.entities.displays.InternalDisplay;
import org.example.enums.Direction;

public class ElevatorCart {
    private int id;
    private int currentFloor;

    private InternalDisplay internalDisplay;

    private ExternalDisplay externalDisplay;
    private Direction direction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public InternalDisplay getInternalDisplay() {
        return internalDisplay;
    }

    public void setInternalDisplay(InternalDisplay internalDisplay) {
        this.internalDisplay = internalDisplay;
    }

    // All args constructor
    public ElevatorCart(int id, int currentFloor, InternalDisplay internalDisplay, Direction direction) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.internalDisplay = internalDisplay;
        this.direction = direction;
    }

    public void moveElevator(int floor) {
        // Move elevator to the specified floor
        this.currentFloor = floor;
    }

    public void pressButton(int floor) {
        // Press the button in the elevator
        internalDisplay.pressButton(floor, this.id);
    }
}
