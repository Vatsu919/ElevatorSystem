package org.example.entities.displays;

import org.example.entities.Dispatcher;
import org.example.enums.Direction;

public class ExternalButton {

    private final Dispatcher dispatcher;

    public ExternalButton() {
        this.dispatcher = Dispatcher.getInstance();
    }

    public void pressButton(int floor, Direction direction) {

        // Submit request to the dispatcher
        dispatcher.submitRequest(floor, direction);
    }
}
