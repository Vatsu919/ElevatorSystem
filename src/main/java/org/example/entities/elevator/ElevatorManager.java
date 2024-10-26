package org.example.entities.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ElevatorManager {
    private final List<ElevatorController> elevatorControllers;

    private static final class InstanceHolder {
        private static final ElevatorManager instance = new ElevatorManager();
    }

    private ElevatorManager() {
        elevatorControllers = new ArrayList<>();
    }

    public static ElevatorManager getInstance() {
        return InstanceHolder.instance;
    }


    public ElevatorController getElevatorController(int id) {
        return elevatorControllers.stream().filter(elevatorController -> elevatorController.getElevatorCart().getId() == id).findFirst().orElse(null);
    }

    public int getElevatorCount() {
        return elevatorControllers.size();
    }

    public void addElevatorController(ElevatorController elevatorController) {
        if(elevatorController == null) {
            return;
        }
        elevatorControllers.add(elevatorController);
    }

    public void removeElevator(int id) {
        if(elevatorControllers.isEmpty()) {
            return;
        }
        elevatorControllers.removeIf(elevatorController -> elevatorController.getElevatorCart().getId() == id);
    }

    public void startElevators() {
        // Give each elevator controller a new thread
        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(elevatorControllers.size());
        elevatorControllers.forEach(ec -> executorService.execute(new Runnable() {
            @Override
            public void run() {
                ec.controlElevator();
            }
        }));

    }
}
