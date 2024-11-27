package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

import java.util.List;

/**
 * Contains the actions that the AI tank will execute.
 * Rules:
 * The AI tank will always target the closest enemy tank.
 * The AI tank will attempt to align itself with the target.
 * The AI tank will attempt to maintain a 75px distance on the vertical axis before moving horizontally to align closer.
 * The AI tank will prioritize vertical alignment over horizontal alignment for movement.
 * If the AI tank has line of sight to the target, it will attempt to fire a missile at the target.
 * ---------------------------------------------------------------------------------------------------------------------
 * Different AI difficulties simply adjust the move and shoot intervals to make the AI tank more or less aggressive for
 * now. More advanced AI difficulties can be implemented in the future.
 */
public class AIDifficulty {
    protected long lastMoveTime; // Randomize the first move time
    protected long lastFireTime; // Randomize the first fire time
    protected int moveInterval; // Randomize the move interval
    protected int shootInterval; // Randomize the shoot interval

    // Execute the AI tank's actions
    public void execute(Tank AITank, Team targetTeam) {
        if (!AITank.isActive()) {
            return; // Do not execute if the AI tank is inactive
        }

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

    // AI Tanks will try to match Y-axis first, then X-axis
    private void moveTowardsAlignment(Tank AITank, Tank targetTank) {
        if (System.currentTimeMillis() - lastMoveTime >= moveInterval) {
            lastMoveTime = System.currentTimeMillis();

            // Calculate deltas
            double deltaX = targetTank.getX() - AITank.getX();
            double deltaY = targetTank.getY() - AITank.getY();

            // Prioritize vertical (Y-axis) alignment over horizontal (X-axis)
            boolean prioritizeVertical = Math.abs(deltaY) > 75;

            if (prioritizeVertical) {
                // Attempt to maintain a 75px distance on the vertical axis
                if (Math.abs(deltaY) > 75) {
                    if (deltaY > 0) {
                        AITank.setState(new Down());
                    } else {
                        AITank.setState(new Up());
                    }
                } else if (Math.abs(deltaX) > 5) { // Tolerance to prevent jitter
                    // Move horizontally to align closer
                    if (deltaX > 0) {
                        AITank.setState(new Right());
                    } else {
                        AITank.setState(new Left());
                    }
                }
            } else {
                // Attempt to maintain a 75px distance on the horizontal axis
                if (Math.abs(deltaX) > 75) {
                    if (deltaX > 0) {
                        AITank.setState(new Right());
                    } else {
                        AITank.setState(new Left());
                    }
                } else if (Math.abs(deltaY) > 5) { // Tolerance to prevent jitter
                    // Move vertically to align closer
                    if (deltaY > 0) {
                        AITank.setState(new Down());
                    } else {
                        AITank.setState(new Up());
                    }
                }
            }
            AITank.move();
        }
    }

    // Check if the AI tank has line of sight to the target tank
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

    // Fires a missile from the AI tank
    public void fireMissile(Tank AITank, Tank targetTank) {
        if (System.currentTimeMillis() - lastFireTime >= shootInterval) {
            lastFireTime = System.currentTimeMillis();
            AITank.fire();
        }
    }

    // Select the closest target tank from the target team
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

    // Calculate the distance between two tanks, used to determine the closest target
    private double calculateDistance(Tank AITank, Tank targetTank) {
        double deltaX = targetTank.getX() - AITank.getX();
        double deltaY = targetTank.getY() - AITank.getY();
        return Math.hypot(deltaX, deltaY);
    }
}
