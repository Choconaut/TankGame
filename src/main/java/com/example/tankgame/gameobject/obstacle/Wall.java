package com.example.tankgame.gameobject.obstacle;

import com.example.tankgame.gameobject.GameObject;

public abstract class Wall extends GameObject {
    public Wall(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();
}
