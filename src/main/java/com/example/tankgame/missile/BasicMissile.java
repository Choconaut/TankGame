package com.example.tankgame.missile;

import com.example.tankgame.direction.Direction;

public class BasicMissile extends Missile {

    public BasicMissile(double x, double y, Direction direction) {
        super(x, y);
        this.state = direction;
    }

    @Override
    public void update() {
        super.update();
        // Other missile-specific logic
    }
}
