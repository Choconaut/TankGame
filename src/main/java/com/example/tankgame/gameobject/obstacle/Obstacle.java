package com.example.tankgame.gameobject.obstacle;

import com.example.tankgame.gameobject.GameObject;

/**
 * The Obstacle class that represents a wall object in the game.
 * Different types of obstacles can be created by extending this class.
 */
public abstract class Obstacle extends GameObject {
    public Obstacle(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();
}
