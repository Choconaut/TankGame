package com.example.tankgame.gameobject.tank;

import com.example.tankgame.direction.Right;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.gameobject.tank.team.Team;

public class PlayerTank extends Tank {
    private long lastMoveTime = System.currentTimeMillis();

    public PlayerTank(double x, double y, Team team, GameObjectManager gameObjectManager) {
        super(x, y, team , gameObjectManager);
        this.state = new Right(); // Default direction is up
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