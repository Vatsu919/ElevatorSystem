package org.example.entities;

import org.example.entities.displays.ExternalButton;
import org.example.entities.displays.ExternalDisplay;
import org.example.enums.Direction;

public class Floor {
    private int id;
    private ExternalButton button;

    public int getId() {
        return id;
    }

    public Floor(int id,ExternalButton button) {
        this.id = id;
        this.button = button;
    }

    public void pressButton(Direction direction) {
        button.pressButton(this.id, direction);
    }

}
