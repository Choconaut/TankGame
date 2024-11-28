package com.example.tankgame.gameobject.tank;

import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.direction.Left;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.gameobject.tank.team.Team;

/**
 * AITank is a subclass of Tank that represents an AI controlled tank in the game.
 *
 * There is no standard limit on the number of parameters you can specify in Java, but according to "Code Complete 2",
 * you should limit the amount of parameters to about 7, anymore and it will have a negative effect on the readability of your code.
 * Therefore, AITank will have only 6 parameters because it is still readable. :)
 */
public class AITank extends Tank {
    private AIDifficulty AIDifficulty;
    private final Team targetTanks;

    // targetTank is the tank that the ai will chase
    public AITank(double x, double y, Team team, Team targetTanks, AIDifficulty AIDifficulty, GameObjectManager gameObjectManager) {
        super(x, y, team , gameObjectManager);
        this.state = new Left(); // Default direction is down
        this.health = 90;
        this.AIDifficulty = AIDifficulty;
        this.targetTanks = targetTanks;
    }

    // Set the AI difficulty, each AI tank can have a different difficulty
    public void setAIDifficulty(AIDifficulty AIDifficulty) {
        this.AIDifficulty = AIDifficulty;
    }

    /**
     * AIDifficulty is an interface that has a method execute that takes in the AITank and the target tank
     * AIDifficulty also manages how the AI tank will move and shoot
     */
    @Override
    public void update() {
        if (isActive() && AIDifficulty != null) {
            AIDifficulty.execute(this, targetTanks);
        }
    }

}
