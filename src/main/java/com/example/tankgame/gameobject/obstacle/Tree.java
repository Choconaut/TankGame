package com.example.tankgame.gameobject.obstacle;

public class Tree extends Obstacle {
    public Tree(double x, double y) {
        super(x + 103, y + 153);
        this.height = 9;
        this.width = 9;
    }

    @Override
    public void update() {}

    @Override
    public String getImagePath() { return "/com/example/tankgame/images/tree.png"; }

    public void handleCollision() {}
}
