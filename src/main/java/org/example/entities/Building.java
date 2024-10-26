package org.example.entities;

import org.example.entities.displays.ExternalButton;
import org.example.entities.displays.InternalDisplay;
import org.example.entities.elevator.ElevatorCart;
import org.example.entities.elevator.ElevatorController;
import org.example.entities.elevator.ElevatorManager;
import org.example.enums.Direction;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Building {
    private final FloorManager floorManager;
    private final ElevatorManager elevatorManager;

    public Building(FloorManager floorManager, ElevatorManager elevatorManager) {
        this.floorManager = floorManager;
        this.elevatorManager = elevatorManager;
    }

    public void addElevators() {
        ElevatorCart elevatorCart1 = new ElevatorCart(0, 0, new InternalDisplay(), Direction.NONE);
        ElevatorCart elevatorCart2 = new ElevatorCart(1, 0, new InternalDisplay(), Direction.NONE);

        BlockingQueue<Integer> upQueue1 = new PriorityBlockingQueue<>();
        // make a max heap
        BlockingQueue<Integer> downQueue1 = new PriorityBlockingQueue<>(100,(a, b) -> b - a);
        BlockingQueue<Integer> upWaitingQueue1 = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> downWaitingQueue1 = new LinkedBlockingQueue<>();
        ElevatorController elevatorController1 = new ElevatorController(elevatorCart1, upQueue1, downQueue1, upWaitingQueue1, downWaitingQueue1);

        BlockingQueue<Integer> upQueue2 = new PriorityBlockingQueue<>();
        // make a max heap
        BlockingQueue<Integer> downQueue2 = new PriorityBlockingQueue<>(100,(a, b) -> b - a);
        BlockingQueue<Integer> upWaitingQueue2 = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> downWaitingQueue2 = new LinkedBlockingQueue<>();
        ElevatorController elevatorController2 = new ElevatorController(elevatorCart2, upQueue2, downQueue2, upWaitingQueue2, downWaitingQueue2);

        elevatorManager.addElevatorController(elevatorController1);
        elevatorManager.addElevatorController(elevatorController2);
    }

    public void addFloors() {
        Floor floor1 = new Floor(0, new ExternalButton());
        Floor floor2 = new Floor(1, new ExternalButton());
        Floor floor3 = new Floor(2, new ExternalButton());
        Floor floor4 = new Floor(3, new ExternalButton());
        Floor floor5 = new Floor(4, new ExternalButton());
        Floor floor6 = new Floor(5, new ExternalButton());
        Floor floor7 = new Floor(6, new ExternalButton());
        Floor floor8 = new Floor(7, new ExternalButton());
        Floor floor9 = new Floor(8, new ExternalButton());
        Floor floor10 = new Floor(9, new ExternalButton());
        Floor floor11 = new Floor(10, new ExternalButton());
        Floor floor12 = new Floor(11, new ExternalButton());
        Floor floor13 = new Floor(12, new ExternalButton());
        Floor floor14 = new Floor(13, new ExternalButton());
        Floor floor15 = new Floor(14, new ExternalButton());
        Floor floor16 = new Floor(15, new ExternalButton());
        Floor floor17 = new Floor(16, new ExternalButton());
        Floor floor18 = new Floor(17, new ExternalButton());
        Floor floor19 = new Floor(18, new ExternalButton());
        Floor floor20 = new Floor(19, new ExternalButton());
        floorManager.addFloor(floor1);
        floorManager.addFloor(floor2);
        floorManager.addFloor(floor3);
        floorManager.addFloor(floor4);
        floorManager.addFloor(floor5);
        floorManager.addFloor(floor6);
        floorManager.addFloor(floor7);
        floorManager.addFloor(floor8);
        floorManager.addFloor(floor9);
        floorManager.addFloor(floor10);
        floorManager.addFloor(floor11);
        floorManager.addFloor(floor12);
        floorManager.addFloor(floor13);
        floorManager.addFloor(floor14);
        floorManager.addFloor(floor15);
        floorManager.addFloor(floor16);
        floorManager.addFloor(floor17);
        floorManager.addFloor(floor18);
        floorManager.addFloor(floor19);
        floorManager.addFloor(floor20);
    }

    public void startElevators() {
        elevatorManager.startElevators();
    }

    public void addFloor(Floor floor) {
        floorManager.addFloor(floor);
    }
}
