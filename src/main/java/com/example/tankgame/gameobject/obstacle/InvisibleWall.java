package com.example.tankgame.gameobject.obstacle;

import com.example.tankgame.gameobject.GameObject;

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

    // Invisible walls are god objects and do not interact with other objects
    @Override
    public void handleCollision(GameObject other) {}

}
