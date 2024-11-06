package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;

public class EnemyTank extends Tank {
    public EnemyTank(double x, double y) {
        super(x, y);
        this.state = new Down(); // Default direction is down
    }

    @Override
    public void update() {

    }
}
