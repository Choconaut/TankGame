package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.gameobject.GameObjectManager;

public class AITank extends Tank {
    private AIDifficulty AIDifficulty;
    private final Tank targetTank;

    // targetTank is the tank that the ai will chase
    public AITank(double x, double y, Tank targetTank, AIDifficulty AIDifficulty, GameObjectManager gameObjectManager) {
        super(x, y, gameObjectManager);
        this.state = new Down(); // Default direction is down
        this.health = 50;
        this.AIDifficulty = AIDifficulty;
        this.targetTank = targetTank;
    }

    // Set the AI difficulty, each AI tank can have a different difficulty
    public void setAIDifficulty(AIDifficulty AIDifficulty) {
        this.AIDifficulty = AIDifficulty;
    }

    /**
     * AIDifficulty is an interface that has a method execute that takes in the AITank and the target tank
     * AIDifficulty also manages how the AI tank will move and shoot
     */
    @Override
    public void update() {
        if (isActive() && AIDifficulty != null) {
            AIDifficulty.execute(this, targetTank);
        }
    }

}
