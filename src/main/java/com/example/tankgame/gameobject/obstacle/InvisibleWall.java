package com.example.tankgame.gameobject.obstacle;

/**
 * The InvisibleWall class that represents an invisible wall object in the game.
 * This wall can have its width and height set to any value.
 */
public class InvisibleWall extends Obstacle {
    public InvisibleWall(double x, double y, int width, int height) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    @Override
    public void update() {}

    @Override
    public String getImagePath() { return null; }

    public void handleCollision() {}
}
