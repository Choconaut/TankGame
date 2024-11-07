package com.example.tankgame.powerup;

import com.example.tankgame.GameObject;
import com.example.tankgame.GameObjectRenderer;
import javafx.scene.Group;

public abstract class PowerUps extends GameObject {

    public PowerUps(double x, double y) {
        super(x, y);
    }

    public abstract String getImagePath();

}
