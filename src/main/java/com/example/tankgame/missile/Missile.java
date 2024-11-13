package com.example.tankgame.missile;

import com.example.tankgame.GameObject;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.tank.Tank;

public abstract class Missile extends GameObject {
    protected int damage;
    protected double speed;
    protected Direction state;
    protected boolean active = true;

    public Missile(double x, double y) {
        super(x, y);
        this.damage = 25;
        this.speed = 10.0;
        this.width = 10;
        this.height = 10; //different sizes based on direction
    }

    @Override
    public void update() {
        this.move();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getImagePath() {
        return this.getState().getImagePath(this);
    }

    public boolean isOutOfBounds(double screenWidth, double screenHeight) {
        return x < 0 || x > screenWidth || y < 0 || y > screenHeight;
    }

    @Override
    public void handleCollision(GameObject other) {
        if (other instanceof Tank) {
            ((Tank) other).setHealth(((Tank) other).getHealth() - this.damage);
            this.setActive(false);
        }
    }

}
