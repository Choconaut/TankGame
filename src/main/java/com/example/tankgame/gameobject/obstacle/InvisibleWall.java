package com.example.tankgame.gameobject.obstacle;

/**
 * This wall is a 50x50 wall that can be placed on the map. It's main purpose is to allow
 * for greater map customization.
 */
public class InvisibleWall extends Wall {
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
