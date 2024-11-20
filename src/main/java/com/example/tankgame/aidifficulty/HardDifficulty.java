package com.example.tankgame.aidifficulty;

import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

import java.util.List;
import java.util.Random;

public class HardDifficulty extends AIDifficulty {
    private Tank currentTarget;
    private final Random random = new Random();

    public HardDifficulty() {
        lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000); // Randomize the first move time
        lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Randomize the first fire time
        moveInterval = 100 + (int)(Math.random() * 300); // Move every 100-400ms
        shootInterval = 1500 + (int)(Math.random() * 3000); // Shoot every 1500-4500ms
    }

    @Override
    public void execute(Tank AITank, Team targetTeam) {
        super.execute(AITank, targetTeam);

        if (currentTarget == null || !currentTarget.isActive()) {
            currentTarget = selectRandomTarget(targetTeam);
        }

        if (currentTarget != null) {
            chase(AITank, currentTarget);
            fireMissile(AITank, currentTarget);
        }
    }

    private Tank selectRandomTarget(Team targetTeam) {
        List<Tank> targetTanks = targetTeam.getTeam();
        if (targetTanks.isEmpty()) {
            return null;
        }
        int index = random.nextInt(targetTanks.size());
        return targetTanks.get(index);
    }
}
