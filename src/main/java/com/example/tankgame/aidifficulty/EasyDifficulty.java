package com.example.tankgame.aidifficulty;

import com.example.tankgame.direction.*;
import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;
import java.util.List;


/**
 * Easy difficulty for AI tanks
 *
 * <p>AI tanks with easy difficulty will move and shoot at a slower pace compared to medium and hard difficulties.
 */
public class EasyDifficulty extends AIDifficulty {
    public EasyDifficulty() {
        lastMoveTime = System.currentTimeMillis() + (int)(Math.random() * 2000); // Random initial move time
        lastFireTime = System.currentTimeMillis() + (int)(Math.random() * 1000); // Random initial fire time
        moveInterval = 500 + (int)(Math.random() * 700); // Move every 500-1200ms
        shootInterval = 2500 + (int)(Math.random() * 5000); // Shoot every 2500-7500ms
    }
}
