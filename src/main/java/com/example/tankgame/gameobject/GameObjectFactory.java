package com.example.tankgame.gameobject;

import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.gameobject.explosion.Explosion;
import com.example.tankgame.gameobject.missile.BasicMissile;
import com.example.tankgame.gameobject.missile.Missile;
import com.example.tankgame.gameobject.obstacle.InvisibleWall;
import com.example.tankgame.gameobject.obstacle.Boulder;
import com.example.tankgame.gameobject.powerup.MedPack;
import com.example.tankgame.gameobject.tank.AITank;
import com.example.tankgame.gameobject.tank.PlayerTank;
import com.example.tankgame.gameobject.tank.team.Team;

/**
 * Factory class to create GameObjects
 */
public class GameObjectFactory {
    private final GameObjectManager gameObjectManager;

    public GameObjectFactory(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    public PlayerTank createPlayerTank(double x, double y, Team team) {
        return new PlayerTank(x, y, team, gameObjectManager);
    }

    /**
     * Create an AI tank
     *
     * @param x, y: the x and y coordinates of the tank
     * @param targetTanks the tanks that the AI tank will target
     * @param AIDifficulty the difficulty of the AI tank
     * @return a new AITank object
     */
    public AITank createAITank(double x, double y, Team team, Team targetTanks, AIDifficulty AIDifficulty) {
        return new AITank(x, y, team ,targetTanks, AIDifficulty, gameObjectManager);
    }

    public MedPack createMedPack(double x, double y) {
        return new MedPack(x, y);
    }

    /**
     * Create a basic missile based on the direction of the tank
     * This is called in fire() method of the tank
     *
     * @param x, the x and y coordinates of the missile
     * @param y
     * @param direction, direction of the tank
     * @return a new missile object
     */
    public static Missile createBasicMissile(double x, double y, Direction direction) {
        Missile missile = new BasicMissile(x, y, direction);
        missile.setState(direction);
        return missile;
    }

    public static Explosion createExplosion(double x, double y) {
        return new Explosion(x, y);
    }

    public Boulder createBoulder(double x, double y) { return new Boulder(x, y); }

    public InvisibleWall createInvisibleWall(double x, double y, int width, int height) { return new InvisibleWall(x, y, width, height); }
}

