package com.example.tankgame.gameobject;

import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.explosion.Explosion;
import com.example.tankgame.missile.BasicMissile;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.AITank;
import com.example.tankgame.tank.PlayerTank;
import com.example.tankgame.tank.Tank;

/**
 * Factory class to create GameObjects
 */
public class GameObjectFactory {
    private final GameObjectManager gameObjectManager;

    public GameObjectFactory(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    public PlayerTank createPlayerTank(double x, double y) {
        return new PlayerTank(x, y, gameObjectManager);
    }

    /**
     * Create an AI tank
     *
     * @param x, y: the x and y coordinates of the tank
     * @param targetTank the tank that the AI tank will target
     * @param AIDifficulty the difficulty of the AI tank
     * @return a new AITank object
     */
    public AITank createAITank(double x, double y, Tank targetTank, AIDifficulty AIDifficulty) {
        return new AITank(x, y, targetTank, AIDifficulty, gameObjectManager);
    }

    public MedPack createMedPack(double x, double y) {
        return new MedPack(x, y);
    }

    /**
     * Create a basic missile based on the direction of the tank
     * This is called in fire() method of the tank
     *
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public static Missile createBasicMissile(double x, double y, Direction direction) {
        Missile missile = new BasicMissile(x, y, direction);
        missile.setState(direction);
        return missile;
    }

    public Explosion createExplosion(double x, double y) {
        return new Explosion(x, y);
    }
}

