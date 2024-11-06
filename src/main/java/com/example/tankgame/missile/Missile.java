package com.example.tankgame.missile;

import com.example.tankgame.GameObject;
import com.example.tankgame.direction.Direction;

public abstract class Missile extends GameObject {
    protected int damage;
    protected double speed;
    protected Direction state;

    public Missile(double x, double y) {
        super(x, y);
        this.damage = 25;
        this.speed = 10.0;
    }

    @Override
    public void update() {
        move();
        // Other missile-specific logic
    }

    public void move() {
        state.move(this, speed);
    }

    public void setState(Direction state) {
        this.state = state;
    }

    public Direction getState() { return state; }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isOutOfBounds(double screenWidth, double screenHeight) {
        return x < 0 || x > screenWidth || y < 0 || y > screenHeight;
    }
}
