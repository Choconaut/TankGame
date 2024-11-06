package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.missile.MissileFactory;
import com.example.tankgame.missile.MissileManager;

public class EnemyTank extends Tank {
    private final MissileManager missileManager;

    public EnemyTank(double x, double y, MissileManager missileManager) {
        super(x, y);
        this.state = new Down(); // Default direction is down
        this.health = 50;
        this.missileManager = missileManager;
    }

    @Override
    public void update() {

    }

    public void fire() {
        Missile missile = MissileFactory.createMissile(this.getX() + 20, this.getY() + 23, this.getState());
        missileManager.addMissile(missile);
    }
}
