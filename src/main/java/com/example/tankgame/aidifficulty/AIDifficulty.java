package com.example.tankgame.aidifficulty;

import com.example.tankgame.tank.Tank;


public interface AIDifficulty {
    // tank will chase and fire upon enemyTank
    void execute(Tank tank, Tank enemyTank);
}
