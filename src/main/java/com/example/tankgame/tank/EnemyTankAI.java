package com.example.tankgame.tank;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;

public class EnemyTankAI {
    private EnemyTank enemyTank;
    private PlayerTank playerTank;
    private long lastMoveTime = System.currentTimeMillis();
    private int moveInterval = 500; // Move every 500ms

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
    }
}
