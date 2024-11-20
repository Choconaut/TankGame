package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

public class MediumDifficulty extends AIDifficulty {

    public MediumDifficulty() {
        lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000);
        lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Randomize the first fire time
        moveInterval = 100 + (int)(Math.random() * 600); // Move every 100-700ms
        shootInterval = 1500 + (int)(Math.random() * 3000); // Shoot every 1500-4500ms
    }

    @Override
    public void execute(Tank AITank, Team targetTanks) {
        super.execute(AITank, targetTanks);

        for (Tank targetTank : targetTanks.getTeam()) {
            while (targetTank.isActive()) {
                chase(AITank, targetTank);
                fireMissile(AITank, targetTank);
            }
        }
    }
}
