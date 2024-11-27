package com.example.tankgame.gameobject.obstacle;

import com.example.tankgame.gameobject.GameObject;

/**
 * Wall class that represents a wall object in the game.
 * Walls are obstacles that tanks and missiles cannot pass through.
 * Different types of walls can be created by extending this class.
 */
public abstract class Wall extends GameObject {
    public Wall(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();
}
