package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;

public class EnemyTankAI {
    private final EnemyTank enemyTank;
    private final PlayerTank playerTank;
    private long lastMoveTime = System.currentTimeMillis();
    private long lastFireTime = System.currentTimeMillis();
    private final int moveInterval = 500 + (int)(Math.random() * 500); // Move every 500-1000ms
    private final int shootInterval = 3000 + (int)(Math.random() * 2000); // Shoot every 3000-5000ms

    public EnemyTankAI(EnemyTank enemyTank, PlayerTank playerTank) {
        this.enemyTank = enemyTank;
        this.playerTank = playerTank;
    }

    public void update() {
        // Only move if the interval has passed
        if (System.currentTimeMillis() - lastMoveTime >= moveInterval) {
            lastMoveTime = System.currentTimeMillis();

            // Calculate the angle between enemy tank and player tank
            double dx = playerTank.getX() - enemyTank.getX();
            double dy = playerTank.getY() - enemyTank.getY();
            double angle = Math.toDegrees(Math.atan2(dy, dx));

            // Set the enemy's direction based on the angle
            if (angle >= -45 && angle < 45) {
                // Right
                enemyTank.setState(new Right());
            } else if (angle >= 45 && angle < 135) {
                // Down
                enemyTank.setState(new Down());
            } else if (angle >= -135 && angle < -45) {
                // Up
                enemyTank.setState(new Up());
            } else {
                // Left
                enemyTank.setState(new Left());
            }

            enemyTank.move();
        }

        // Shoot at the player every shootInterval
        if (System.currentTimeMillis() - lastFireTime >= shootInterval) {
            double tolerance = 50.0; // Allow a bit of tolerance for firing
            if (Math.abs(enemyTank.getX() - playerTank.getX()) < tolerance ||
                    Math.abs(enemyTank.getY() - playerTank.getY()) < tolerance) {
                if (Math.random() < 0.5) { // 50% chance to fire
                    lastFireTime = System.currentTimeMillis();
                    enemyTank.fire();
                }
            }

        }
    }
}
