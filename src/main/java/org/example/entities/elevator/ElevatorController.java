package org.example.entities.elevator;

import org.example.enums.Direction;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class ElevatorController {
    private final ElevatorCart elevatorCart;
    private final BlockingQueue<Integer> upQueue;
    private final BlockingQueue<Integer> downQueue;

    private final BlockingQueue<Integer> upWaitingQueue;
    private final BlockingQueue<Integer> downWaitingQueue;

    public ElevatorController(ElevatorCart elevatorCart, BlockingQueue<Integer> upQueue, BlockingQueue<Integer> downQueue, BlockingQueue<Integer> upWaitingQueue, BlockingQueue<Integer> downWaitingQueue) {
        this.elevatorCart = elevatorCart;
        this.upQueue = upQueue;
        this.downQueue = downQueue;
        this.upWaitingQueue = upWaitingQueue;
        this.downWaitingQueue = downWaitingQueue;
    }

    public ElevatorCart getElevatorCart() {
        return elevatorCart;
    }

    public void submitRequest(int floor, Direction dir) {
        System.out.println("Request received for floor: "+floor+" in direction: "+dir);
        if(dir == Direction.UP) {
            if(elevatorCart.getDirection() == Direction.UP && floor>=elevatorCart.getCurrentFloor()) {
                upQueue.add(floor);
            } else if(elevatorCart.getDirection() == Direction.UP && floor<elevatorCart.getCurrentFloor()) {
                upWaitingQueue.add(floor);
            } else {
                upQueue.add(floor);
            }
        }
        else if(dir == Direction.DOWN) {
            if(elevatorCart.getDirection() == Direction.DOWN && floor<=elevatorCart.getCurrentFloor()) {
                downQueue.add(floor);
            } else if(elevatorCart.getDirection() == Direction.DOWN && floor>elevatorCart.getCurrentFloor()) {
                downWaitingQueue.add(floor);
            }  else {
                downQueue.add(floor);
            }
        }
        else {
            if(elevatorCart.getDirection() == Direction.UP && floor>= elevatorCart.getCurrentFloor()) {
                upQueue.add(floor);
            }
            else if(elevatorCart.getDirection() == Direction.UP && floor < elevatorCart.getCurrentFloor()) {
                downQueue.add(floor);
            }
            else if(elevatorCart.getDirection() == Direction.DOWN && floor<= elevatorCart.getCurrentFloor()) {
                downQueue.add(floor);
            }
            else if(elevatorCart.getDirection() == Direction.DOWN && floor > elevatorCart.getCurrentFloor()) {
                upQueue.add(floor);
            }
            else {
                if(floor > elevatorCart.getCurrentFloor()) {
                    upQueue.add(floor);
                } else {
                    downQueue.add(floor);
                }
            }
        }
    }

    public void controlElevator() {
        System.out.println("Starting elevator "+elevatorCart.getId());
        while(true) {
            if(Objects.nonNull(upQueue) && upQueue.size()>0) {
                while(elevatorCart.getCurrentFloor() > upQueue.peek()) {
                    System.out.println("Elevator "+elevatorCart.getId()+" is at floor: "+elevatorCart.getCurrentFloor());
                    elevatorCart.setDirection(Direction.DOWN);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        break;
                    }
                    elevatorCart.moveElevator(elevatorCart.getCurrentFloor()-1);

                }
                while(upQueue.size()>0) {
                    System.out.println("Elevator "+elevatorCart.getId()+" is at floor: "+elevatorCart.getCurrentFloor());
                    elevatorCart.setDirection(Direction.UP);
                    if(elevatorCart.getCurrentFloor() == upQueue.peek()) {
                        upQueue.poll();
                        // wait for floor selection for that elevator

                    }
                    else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();e.printStackTrace();
                            Thread.currentThread().interrupt();
                            break;

                        }
                        elevatorCart.moveElevator(elevatorCart.getCurrentFloor()+1);
                    }
                }
            }
            while(Objects.nonNull(upWaitingQueue) && upWaitingQueue.size()>0) {
                upQueue.add(upWaitingQueue.poll());
            }
            if(Objects.nonNull(downQueue) && downQueue.size()>0) {
                while(elevatorCart.getCurrentFloor()<downQueue.peek()) {
                    System.out.println("Elevator "+elevatorCart.getId()+" is at floor: "+elevatorCart.getCurrentFloor());
                    elevatorCart.setDirection(Direction.UP);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        break;
                    }
                    elevatorCart.moveElevator(elevatorCart.getCurrentFloor()+1);
                }
                while(downQueue.size()>0) {
                    System.out.println("Elevator "+elevatorCart.getId()+" is at floor: "+elevatorCart.getCurrentFloor());
                    elevatorCart.setDirection(Direction.DOWN);
                    if(elevatorCart.getCurrentFloor() == downQueue.peek()) {
                        downQueue.poll();
                    }
                    else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            break;
                        }
                        elevatorCart.moveElevator(elevatorCart.getCurrentFloor()-1);
                    }
                }
            }
            while(Objects.nonNull(downWaitingQueue) && downWaitingQueue.size()>0) {
                downQueue.add(downWaitingQueue.poll());
            }
        }
    }
}
