package com.example.tankgame.tank;

import com.example.tankgame.tank.directions.Up;

public class PlayerTank extends Tank {

    public PlayerTank(double x, double y) {
        super(x, y);
        this.state = new Up(); // Default direction is up
    }

    @Override
    public void update() {
        // Player-specific logic if needed
    }
}