package com.anish.calabashbros;

import java.awt.*;

public class Obstacle extends Thing {

    public Obstacle(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
    }
}
