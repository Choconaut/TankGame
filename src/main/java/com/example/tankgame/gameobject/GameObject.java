package com.example.tankgame.gameobject;

/**
 * GameObject is the base class for all objects in the game.
 * It contains the x and y coordinates of the object, as well as the width and height.
 * It also contains a boolean isActive to determine if the object is active or not.
 * isActive is used to determine if the object should be removed from the game or kept updated.
 */
public abstract class GameObject {
    // Position of the object
    protected double x;
    protected double y;

    // Width and height of the object
    protected int width;
    protected int height;

    // isActive is used to determine if the object is active or not. Inactive objects are removed from the game.
    protected boolean isActive = true;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected abstract void update();

    /**
     * Getters and Setters
     * <p>
     * getX is the initial x coordinate of the object. getMaxX is the x coordinate plus the width of the object.
     * This is used to determine if any x coordinates of other objects are within the x coordinates of this object.
     * rather than checking if the x coordinate of one object is equal to the x coordinate of another object.
     */
    public double getX() { return x; }

    public double getY() { return y; }

    public double getMaxX() { return x + width; }

    public double getMaxY() { return y + height; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public void setActive(boolean active) { this.isActive = active; }

    public boolean isActive() { return this.isActive; }

    // The getImagePath method should be overridden by subclasses to return the path to the image of the object.
    protected abstract String getImagePath();

    // The intercepts method checks if the object is intercepting another object.
    public boolean intercepts(GameObject otherObject) {
        return this.x < otherObject.getMaxX() &&
                this.getMaxX() > otherObject.getX() &&
                this.y < otherObject.getMaxY() &&
                this.getMaxY() > otherObject.getY();
    }

    // The default implementation that should be overridden by subclasses.
    public void handleCollision(GameObject otherObject) {
        System.out.println("Collision detected");
    }

}
