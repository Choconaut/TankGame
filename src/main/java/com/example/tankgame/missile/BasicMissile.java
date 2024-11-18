package com.example.tankgame.missile;

import com.example.tankgame.direction.Direction;

/**
 * BasicMissile is a simple missile that moves in a straight line.
 *
 * Most of the missile stats are defined in the Missile class.
 * This class is used to define the missile-specific logic.
 */
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
