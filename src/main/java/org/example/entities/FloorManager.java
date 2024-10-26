package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class FloorManager {

    private final List<Floor> floors;

    private static FloorManager instance;

    public static FloorManager getInstance() {
        if(instance==null) {
            synchronized (FloorManager.class) {
                if(instance==null) {
                    instance = new FloorManager();
                }
            }
        }
        return instance;
    }

    private FloorManager() {
        floors = new ArrayList<>();
    }

    public Floor getFloor(int id) {
        return floors.stream().filter(floor -> floor.getId() == id).findFirst().orElse(null);
    }

    public void addFloor(Floor floor) {
        if(floors == null) {
            return;
        }
        floors.add(floor);
    }

    public int getFloorCount() {
        if (floors == null) {
            return -1;
        }
        return floors.size();
    }
}
