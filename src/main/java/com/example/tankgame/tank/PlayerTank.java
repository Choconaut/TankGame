package com.example.tankgame.tank;

import com.example.tankgame.direction.Up;
import com.example.tankgame.gameobject.GameObjectManager;

public class PlayerTank extends Tank {
    private long lastMoveTime = System.currentTimeMillis();

    public PlayerTank(double x, double y, GameObjectManager gameObjectManager) {
        super(x, y, gameObjectManager);
        this.state = new Up(); // Default direction is up
    }

    @Override
    public void update() {
        // Explosion, then You Died
    }

    /**
     * shootInterval is to prevent the player from spam shooting
     * uses the fire method from the Tank class
     */
    public void fire() {
        int shootInterval = 1000;
        if (System.currentTimeMillis() - lastMoveTime >= shootInterval) {
            lastMoveTime = System.currentTimeMillis();
            super.fire();
        }
    }
}