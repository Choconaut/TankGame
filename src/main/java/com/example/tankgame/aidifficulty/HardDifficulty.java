package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.*;
import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

import java.util.List;

public class HardDifficulty extends AIDifficulty {

    public HardDifficulty() {
        lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000); // Random initial move time
        lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Random initial fire time
        moveInterval = 100 + (int)(Math.random() * 300); // Move every 100-400ms
        shootInterval = 1500 + (int)(Math.random() * 3000); // Shoot every 1500-4500ms
    }

    @Override
    public void execute(Tank AITank, Team targetTeam) {
        super.execute(AITank, targetTeam);

        // Update target every time to always target the closest enemy
        Tank currentTarget = selectClosestTarget(AITank, targetTeam);

        if (currentTarget != null) {
            if (hasLineOfSight(AITank, currentTarget)) {
                // If aligned, attempt to fire
                fireMissile(AITank, currentTarget);
            } else {
                // Move towards alignment
                moveTowardsAlignment(AITank, currentTarget);
            }
        }
    }

    private void moveTowardsAlignment(Tank AITank, Tank targetTank) {
        if (System.currentTimeMillis() - lastMoveTime >= moveInterval) {
            lastMoveTime = System.currentTimeMillis();

            // Decide whether to move horizontally or vertically
            double deltaX = targetTank.getX() - AITank.getX();
            double deltaY = targetTank.getY() - AITank.getY();

            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                // Move horizontally
                if (deltaX > 0) {
                    AITank.setState(new Right());
                } else {
                    AITank.setState(new Left());
                }
            } else {
                // Move vertically
                if (deltaY > 0) {
                    AITank.setState(new Down());
                } else {
                    AITank.setState(new Up());
                }
            }
            AITank.move();
        }
    }

    private boolean hasLineOfSight(Tank AITank, Tank targetTank) {
        double tolerance = 5.0; // Adjust as needed

        // Check horizontal alignment
        boolean alignedHorizontally = Math.abs(AITank.getY() - targetTank.getY()) < tolerance;
        // Check vertical alignment
        boolean alignedVertically = Math.abs(AITank.getX() - targetTank.getX()) < tolerance;

        if (alignedHorizontally) {
            // Set direction towards the target
            if (AITank.getX() < targetTank.getX()) {
                AITank.setState(new Right());
            } else {
                AITank.setState(new Left());
            }
            return true;
        } else if (alignedVertically) {
            if (AITank.getY() < targetTank.getY()) {
                AITank.setState(new Down());
            } else {
                AITank.setState(new Up());
            }
            return true;
        }
        return false;
    }

    @Override
    public void fireMissile(Tank AITank, Tank targetTank) {
        if (System.currentTimeMillis() - lastFireTime >= shootInterval) {
            lastFireTime = System.currentTimeMillis();
            AITank.fire();
        }
    }

    private Tank selectClosestTarget(Tank AITank, Team targetTeam) {
        List<Tank> targetTanks = targetTeam.getTeam();
        if (targetTanks.isEmpty()) {
            return null;
        }

        Tank closestTank = null;
        double minDistance = Double.MAX_VALUE;

        for (Tank tank : targetTanks) {
            if (tank.isActive()) {
                double distance = calculateDistance(AITank, tank);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestTank = tank;
                }
            }
        }
        return closestTank;
    }

    private double calculateDistance(Tank AITank, Tank targetTank) {
        double deltaX = targetTank.getX() - AITank.getX();
        double deltaY = targetTank.getY() - AITank.getY();
        return Math.hypot(deltaX, deltaY);
    }
}
