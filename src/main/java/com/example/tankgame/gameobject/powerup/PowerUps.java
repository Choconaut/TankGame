package com.example.tankgame.gameobject.powerup;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.gameobject.tank.Tank;

/**
 * PowerUps is the parent class for all powerups in the game.
 */
public abstract class PowerUps extends GameObject {

    public PowerUps(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();

    public abstract void handleCollision(Tank tank);

    public abstract void applyEffect(Tank tank);
}
