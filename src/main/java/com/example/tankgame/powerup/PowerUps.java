package com.example.tankgame.powerup;

import com.example.tankgame.GameObject;
import com.example.tankgame.tank.Tank;

public abstract class PowerUps extends GameObject {

    private boolean active = true;

    public PowerUps(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();

    public abstract void handleCollision(Tank tank);

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }
}
