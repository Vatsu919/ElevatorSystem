package org.example.entities.displays;

import org.example.entities.Dispatcher;
import org.example.enums.Direction;

public class InternalDisplay {
    private final Dispatcher dispatcher;

    public InternalDisplay() {
        this.dispatcher = Dispatcher.getInstance();
    }

    public void pressButton(int floor, int elevatorId) {
        // Submit request to the dispatcher
        dispatcher.submitRequest(floor, Direction.NONE, elevatorId);
    }
}
