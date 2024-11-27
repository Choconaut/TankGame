package com.example.tankgame.gameobject.obstacle;

/**
 * This wall is a 50x50 wall that can be placed on the map. It's main purpose is to allow
 * for greater map customization.
 */
public class InvisibleWall extends Wall {
    public InvisibleWall(double x, double y) {
        super(x, y);
        this.height = 50;
        this.width = 50;
    }

    @Override
    public void update() {}

    @Override
    public String getImagePath() { return null; }

    public void handleCollision() {}
}
