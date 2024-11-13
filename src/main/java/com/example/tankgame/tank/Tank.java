package com.example.tankgame.tank;

import com.example.tankgame.GameObject;
import com.example.tankgame.direction.Direction;


public abstract class Tank extends GameObject {
    protected int health;
    protected double speed;
    protected Direction state;

    public Tank(double x, double y) {
        super(x, y);
        this.health = 100;
        this.speed = 5.0;
        this.width = 50;
        this.height = 50;
    }

    public void move() {
        state.move(this, speed);
    }

    public void setState(Direction state) {
        this.state = state;
    }

    public Direction getState() {
        return state;
    }

    public double getSpeed() {
        return speed;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }

    @Override
    public String getImagePath() {
        return this.getState().getImagePath(this);
    }
}
