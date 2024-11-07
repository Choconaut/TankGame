package com.example.tankgame;

import javafx.geometry.Bounds;

public abstract class GameObject {
    protected double x;
    protected double y;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public double getX() { return x; }

    public double getY() { return y; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public abstract String getImagePath();

    public Bounds getBounds(GameObjectRenderer renderer) {
        return renderer.getImageView().getBoundsInParent();
    }

    public void handleCollision(GameObject obj2){

    }
}
