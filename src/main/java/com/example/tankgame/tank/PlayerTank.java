package com.example.tankgame.tank;

import com.example.tankgame.direction.Up;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.missile.MissileManager;
import com.example.tankgame.GameObjectFactory;

public class PlayerTank extends Tank {
    private final MissileManager missileManager;
    private long lastMoveTime = System.currentTimeMillis();

    public PlayerTank(double x, double y, MissileManager missileManager) {
        super(x, y);
        this.state = new Up(); // Default direction is up
        this.missileManager = missileManager;
    }

    @Override
    public void update() {
        // Explosion, then You Died
    }

    public void fire() {
        int shootInterval = 500;
        if (System.currentTimeMillis() - lastMoveTime >= shootInterval) {
            lastMoveTime = System.currentTimeMillis();
            Missile missile = GameObjectFactory.createBasicMissile(this.getX() + 20, this.getY() + 23, this.getState());
            missileManager.addMissile(missile);
        }
    }
}