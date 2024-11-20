package com.example.tankgame.gameobject.tank;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.gameobject.GameObjectFactory;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.gameobject.GameObjectContainer;
import com.example.tankgame.gameobject.missile.Missile;
import com.example.tankgame.gameobject.powerup.MedPack;
import com.example.tankgame.gameobject.tank.team.Team;

/**
 * This class represents a tank in the game.
 * It has health, speed, and a state that determines its direction.
 * It can move, fire missiles, and handle collisions with other game objects.
 * This class will be extended by different types of tanks in the game.
 * <p>
 * The default stats of a tank are:
 * - health: 100
 * - speed: 2.5, moves 2.5 pixels per frame
 * - width: 50, the tank's image width
 * - height: 50, the tank's image height
 */
public abstract class Tank extends GameObject {
    protected final GameObjectContainer gameObjectContainer;
    protected int health;
    protected double speed;
    protected Direction state;
    protected Team team;

    public Tank(double x, double y, Team team, GameObjectContainer gameObjectContainer) {
        super(x, y);
        this.gameObjectContainer = gameObjectContainer;
        this.team = team;
        this.health = 100;
        this.speed = 2.5;
        this.width = 50;
        this.height = 50;
        team.addTank(this);
    }

    // Move the tank based on its current state and speed
    public void move() {
        state.move(this, speed);
    }

    // Set the tank's state to the specified direction
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

    /**
     * The default firing implementation for tanks, creates a basic missile at the tank's position.
     * The missile's position is offset by the tank's state.
     * Each of the four tank states has a different offset for the missile's position due to
     * the tank's image having a different orientation for each state.
     *
     * Perhaps a bad implementation since Tank now knows about every single gameObject in the gameObjectContainer.
      */
    public void fire() {
        Missile missile = GameObjectFactory.createBasicMissile(
                this.getX() + this.state.getOffsetX(),
                this.getY() + this.state.getOffsetY(),
                this.getState());
        gameObjectContainer.addGameObject(missile);
    }

    // Get the image path for the tank based on its current state
    @Override
    public String getImagePath() {
        return this.getState().getImagePath(this);
    }

    // Handle collisions with other game objects, calls the appropriate method based on the object type
    @Override
    public void handleCollision(GameObject other) {
        if (other instanceof Missile) {
            this.collideWithMissile((Missile) other);
        } else if (other instanceof MedPack) {
            this.collideWithMedPack((MedPack) other);
        }
    }

    /**
     * Handle a collision with a missile, reduces the tank's health by the missile's damage
     * and marks the tank as inactive if its health is less than or equal to 0. When
     * a tank is marked as inactive, it will be removed from the game.
      */
    public void collideWithMissile(Missile missile) {
        this.setHealth(this.getHealth() - missile.getDamage());
        System.out.println("Tank hit! Health: " + this.getHealth());

        if (this.getHealth() <= 0) {
            this.setActive(false); // Mark tank as inactive
        }
    }

    // Handle a collision with a med pack, applies a health boost to the tank
    public void collideWithMedPack(MedPack medPack) {
        medPack.applyEffect(this);
    }

}
