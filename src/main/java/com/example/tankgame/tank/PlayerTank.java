package com.example.tankgame.tank;

import com.example.tankgame.direction.Up;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.missile.MissileFactory;
import com.example.tankgame.missile.MissileManager;

public class PlayerTank extends Tank {
    private MissileManager missileManager;
    private int shootInterval = 500;
    private long lastMoveTime = System.currentTimeMillis();

    public PlayerTank(double x, double y, MissileManager missileManager) {
        super(x, y);
        this.state = new Up(); // Default direction is up
        this.missileManager = missileManager;
    }

    @Override
    public void update() {
        // Player-specific logic if needed
    }

    public void fire() {
        if (System.currentTimeMillis() - lastMoveTime >= shootInterval) {
            lastMoveTime = System.currentTimeMillis();
            Missile missile = MissileFactory.createMissile(this.getX() + 20, this.getY() + 23, this.getState());
            missileManager.addMissile(missile);
        }
    }
}