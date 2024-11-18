package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import com.example.tankgame.tank.Tank;

public class EasyDifficulty implements AIDifficulty {
    private long lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 200); // Randomize the first move time
    private long lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 3000); // Randomize the first fire time
    private final int moveInterval = 500 + (int)(Math.random() * 500); // Move every 500-1000ms
    private final int shootInterval = 5000 + (int)(Math.random() * 3000); // Shoot every 5000-8000ms

    @Override
    public void execute(Tank tank, Tank targetTank) {
        if (!tank.isActive()) {
            return; // Do not execute if the enemy tank is inactive
        }

        // Only move if the interval has passed
        if (System.currentTimeMillis() - lastMoveTime >= moveInterval) {
            lastMoveTime = System.currentTimeMillis();

            // Calculate the angle between enemy tank and player tank
            double dx = targetTank.getX() - tank.getX();
            double dy = targetTank.getY() - tank.getY();
            double angle = Math.toDegrees(Math.atan2(dy, dx));

            // Set the enemy's direction based on the angle
            if (angle >= -45 && angle < 45) {
                // Right
                tank.setState(new Right());
            } else if (angle >= 45 && angle < 135) {
                // Down
                tank.setState(new Down());
            } else if (angle >= -135 && angle < -45) {
                // Up
                tank.setState(new Up());
            } else {
                // Left
                tank.setState(new Left());
            }

            tank.move();
        }

        // Shoot at the player every shootInterval
        if (System.currentTimeMillis() - lastFireTime >= shootInterval) {
            double tolerance = 30.0; // Allow a bit of tolerance for firing
            if (Math.abs(tank.getX() - targetTank.getX()) < tolerance ||
                    Math.abs(tank.getY() - targetTank.getY()) < tolerance) {
                if (Math.random() < 0.5) { // 50% chance to fire
                    lastFireTime = System.currentTimeMillis();
                    tank.fire();
                }
            }
        }
    }
}