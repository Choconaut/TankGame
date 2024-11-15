package com.example.tankgame.explosion;

import com.example.tankgame.GameObject;

public class Explosion extends GameObject {
    public Explosion(double x, double y) {
        super(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/0.gif";
    }
}
