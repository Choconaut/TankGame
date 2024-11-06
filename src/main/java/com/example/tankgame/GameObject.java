package com.example.tankgame;

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

    public void checkCollision(GameObject otherObject) {

    }

}
