package com.example.tankgame.direction;

import com.example.tankgame.gameobject.GameObject;

public interface Direction {

    void move(GameObject object, double speed);

    String getImagePath(GameObject object);

    int getOffsetX();

    int getOffsetY();
}
