package com.example.tankgame.aidifficulty;

public class MediumDifficulty extends AIDifficulty {
    public MediumDifficulty() {
        lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000); // Random initial move time
        lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Random initial fire time
        moveInterval = 300 + (int)(Math.random() * 600); // Move every 300-900ms
        shootInterval = 1500 + (int)(Math.random() * 3000); // Shoot every 1500-4500ms
    }
}
