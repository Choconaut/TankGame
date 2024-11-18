package com.example.tankgame;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean isActive = true;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public double getX() { return x; }

    public double getY() { return y; }

    public double getMaxX() { return x + width; }

    public double getMaxY() { return y + height; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public void setActive(boolean active) { this.isActive = active; }

    public boolean isActive() { return this.isActive; }

    public abstract String getImagePath();

    public boolean intercepts(GameObject otherObject) {
        return this.x < otherObject.getMaxX() &&
                this.getMaxX() > otherObject.getX() &&
                this.y < otherObject.getMaxY() &&
                this.getMaxY() > otherObject.getY();
    }

    public void handleCollision(GameObject otherObject) {
        System.out.println("Collision detected");
    }

}
