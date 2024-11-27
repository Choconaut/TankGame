package com.example.tankgame.aidifficulty;

import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

// Abstract class for difficulties of AI tanks
public abstract class AIDifficulty {
    protected long lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 200); // Randomize the first move time
    protected long lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 3000); // Randomize the first fire time
    protected int moveInterval = 500 + (int)(Math.random() * 500); // Move every 500-1000ms
    protected int shootInterval = 5000 + (int)(Math.random() * 3000); // Shoot every 5000-8000ms

    public void execute(Tank AITank, Team targetTeam) {
        if (!AITank.isActive()) {
            return; // Do not execute if the AI tank is inactive
        }
    }

    public abstract void fireMissile(Tank AITank, Tank targetTank);
}
