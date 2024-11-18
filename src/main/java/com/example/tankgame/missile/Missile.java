package com.example.tankgame.missile;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.tank.Tank;

/**
 * Missile class that represents a missile object in the game.
 * Missiles are fired by tanks and can collide with other tanks and walls.
 * Missiles have a damage value and a speed value.
 * Different types of missiles can be created by extending this class.
 *
 * The default stats for a missile are:
 * - Damage: 20
 * - Speed: 10.0
 * - Width: 10
 * - Height: 10
 */
public abstract class Missile extends GameObject {
    protected int damage;
    protected double speed;
    protected Direction state;

    /**
     * The width/height is inaccurate due to the fact that the missile is not a square.
     * @param x
     * @param y
     */
    public Missile(double x, double y) {
        super(x, y);
        this.damage = 20;
        this.speed = 10.0;
        this.width = 10;
        this.height = 10; //different sizes based on direction
    }

    @Override
    public void update() {
        this.move();
        // Other missile-specific logic
    }

    // Move the missile in the direction it is facing
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

    @Override
    public String getImagePath() {
        return this.getState().getImagePath(this);
    }

    // Handle collisions with other game objects, calls the appropriate method based on the object type
    @Override
    public void handleCollision(GameObject other) {
        if (other instanceof Tank) {
            this.collideWithTank((Tank) other);
        }
        this.setActive(false);
    }

    // Handle collision with a tank, sets the missile to false since there's no need to keep it active
    public void collideWithTank(Tank tank) {
        tank.setHealth(tank.getHealth() - this.damage);
        this.setActive(false);
    }


}
