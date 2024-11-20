package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;


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

    public void chase(Tank AITank, Tank targetTank) {
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
    }

    public void fireMissile(Tank AITank, Tank targetTank) {
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
