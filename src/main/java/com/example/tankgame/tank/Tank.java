package com.example.tankgame.tank;

import com.example.tankgame.GameObject;


public abstract class Tank extends GameObject {
    protected int health;
    protected double speed;
    protected TankDirection state;

    public Tank(double x, double y) {
        super(x, y);
        this.health = 100;
        this.speed = 5.0;
    }

    @Override
    public void update() {
        // Other tank-specific logic
    }

    public void move() {
        state.move(this);
    }

    public void setState(TankDirection state) {
        this.state = state;
    }

    public TankDirection getState() {
        return state;
    }

    public double getSpeed() {
        return speed;
    }
}
