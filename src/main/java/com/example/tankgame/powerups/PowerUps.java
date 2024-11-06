package com.example.tankgame.powerups;

import com.example.tankgame.GameObject;

public abstract class PowerUps extends GameObject {

    public PowerUps(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();
}
