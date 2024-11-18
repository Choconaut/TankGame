package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.gameobject.GameObjectManager;

public class AITank extends Tank {
    private AIDifficulty AIDifficulty;
    private final Tank targetTank;

    public AITank(double x, double y, Tank targetTank, AIDifficulty AIDifficulty, GameObjectManager gameObjectManager) {
        super(x, y, gameObjectManager);
        this.state = new Down(); // Default direction is down
        this.health = 50;
        this.AIDifficulty = AIDifficulty;
        this.targetTank = targetTank;
    }

    public void setAIDifficulty(AIDifficulty AIDifficulty) {
        this.AIDifficulty = AIDifficulty;
    }

    @Override
    public void update() {
        if (isActive() && AIDifficulty != null) {
            AIDifficulty.execute(this, targetTank);
        }
    }

}
