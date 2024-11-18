package com.example.tankgame.tank;

import com.example.tankgame.GameObject;
import com.example.tankgame.GameObjectFactory;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.powerup.MedPack;


public abstract class Tank extends GameObject {
    protected final GameObjectManager gameObjectManager;
    protected int health;
    protected double speed;
    protected Direction state;

    public Tank(double x, double y, GameObjectManager gameObjectManager) {
        super(x, y);
        this.gameObjectManager = gameObjectManager;
        this.health = 100;
        this.speed = 2.5;
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

    public void fire() {
        Missile missile = GameObjectFactory.createBasicMissile(
                this.getX() + this.state.getOffsetX(),
                this.getY() + this.state.getOffsetY(),
                this.getState());
        gameObjectManager.addGameObject(missile);
    }

    @Override
    public String getImagePath() {
        return this.getState().getImagePath(this);
    }

    @Override
    public void handleCollision(GameObject other) {
        if (other instanceof Missile) {
            this.collideWithMissile((Missile) other);
        } else if (other instanceof MedPack) {
            this.collideWithMedPack((MedPack) other);
        }
    }

    public void collideWithMissile(Missile missile) {
        this.setHealth(this.getHealth() - missile.getDamage());
        System.out.println("Tank hit! Health: " + this.getHealth());

        if (this.getHealth() <= 0) {
            this.setActive(false); // Mark tank as inactive
        }
    }

    public void collideWithMedPack(MedPack medPack) {
        medPack.heal(this);
    }

}
