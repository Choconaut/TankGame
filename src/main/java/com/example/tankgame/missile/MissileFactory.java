package com.example.tankgame.missile;

import com.example.tankgame.direction.Direction;

public class MissileFactory {
    public static Missile createMissile(double x, double y, Direction direction) {
        Missile missile = new BasicMissile(x, y, direction);
        missile.setState(direction);
        return missile;
    }
}
