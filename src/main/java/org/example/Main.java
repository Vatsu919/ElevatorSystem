package org.example;

import org.example.entities.Building;
import org.example.entities.Floor;
import org.example.entities.FloorManager;
import org.example.entities.elevator.ElevatorManager;
import org.example.enums.Direction;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FloorManager floorManager = FloorManager.getInstance();
        ElevatorManager elevatorManager = ElevatorManager.getInstance();

        System.out.println("Enter the number of floors in the building");
        Building building = new Building(floorManager, elevatorManager);
        building.addFloors();
        building.addElevators();

        building.startElevators();

        System.out.println("Enter the number of people in the building");

        floorManager.getFloor(6).pressButton(Direction.UP);
        floorManager.getFloor(3).pressButton(Direction.DOWN);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorManager.getElevatorController(0).getElevatorCart().pressButton(8);
    }
}