package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.gameobject.GameObjectManager;

public class EnemyTank extends Tank {

    public EnemyTank(double x, double y, GameObjectManager gameObjectManager) {
        super(x, y, gameObjectManager);
        this.state = new Down(); // Default direction is down
        this.health = 50;
    }

    @Override
    public void update() {

    }

}
