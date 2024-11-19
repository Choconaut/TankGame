package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import com.example.tankgame.tank.Tank;

public class HardDifficulty implements AIDifficulty {

    private long lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000); // Randomize the first move time
    private long lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Randomize the first fire time
    private final int moveInterval = 100 + (int)(Math.random() * 300); // Move every 100-400ms
    private final int shootInterval = 1500 + (int)(Math.random() * 3000); // Shoot every 1500-4500ms

    @Override
    public void execute(Tank AITank, Tank targetTank) {
        if (!AITank.isActive()) {
            return; // Do not execute if the AI tank is inactive
        }

        // Only move if the interval has passed
        if (System.currentTimeMillis() - lastMoveTime >= moveInterval) {
            lastMoveTime = System.currentTimeMillis();

            // Calculate the angle between AI tank and target tank
            double dx = targetTank.getX() - AITank.getX();
            double dy = targetTank.getY() - AITank.getY();
            double angle = Math.toDegrees(Math.atan2(dy, dx));

            // Set the enemy's direction based on the angle
            if (angle >= -45 && angle < 45) {
                // Right
                AITank.setState(new Right());
            } else if (angle >= 45 && angle < 135) {
                // Down
                AITank.setState(new Down());
            } else if (angle >= -135 && angle < -45) {
                // Up
                AITank.setState(new Up());
            } else {
                // Left
                AITank.setState(new Left());
            }

            AITank.move();
        }

        // Shoot at the player every shootInterval
        if (System.currentTimeMillis() - lastFireTime >= shootInterval) {
            double tolerance = 30.0; // Allow a bit of tolerance for firing
            if (Math.abs(AITank.getX() - targetTank.getX()) < tolerance ||
                    Math.abs(AITank.getY() - targetTank.getY()) < tolerance) {
                if (Math.random() < 0.5) { // 50% chance to fire
                    lastFireTime = System.currentTimeMillis();
                    AITank.fire();
                }
            }
        }
    }
}
