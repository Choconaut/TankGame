package com.example.tankgame.gameobject.obstacle;

public class SquareWall extends Wall {
    public SquareWall(double x, double y) {
        super(x, y);
        this.height = 50;
        this.width = 50;
    }

    @Override
    public void update() {

    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/hedge.png";
    }

    public void handleCollision() {

    }
}
