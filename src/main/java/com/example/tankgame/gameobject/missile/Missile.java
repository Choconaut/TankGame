package com.example.tankgame.gameobject.missile;

import com.example.tankgame.GameConstants;
import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.gameobject.tank.Tank;

/**
 * Missile class that represents a missile object in the game.
 * Missiles are fired by tanks and can collide with other tanks and walls.
 * Missiles have a damage value and a speed value.
 * Different types of missiles can be created by extending this class.
 *
 * The default stats for a missile are:
 * - Damage: 30
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
        this.damage = 30;
        this.speed = 10.0;
        this.width = 10;
        this.height = 10; //different sizes based on direction
    }

    @Override
    public void update() {
        this.move();

        // Remove if the missile is out of bounds
        //20 is because of a UI bug where the screen "bounces" when the missile goes out of bounds
        if (this.x < 10 || this.x > GameConstants.gameWidth - 20 ||
                this.y < 10 || this.y > GameConstants.gameHeight) {
            this.setActive(false);
        }
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
        this.setActive(false);
    }
}
