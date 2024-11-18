package com.example.tankgame;

import com.example.tankgame.aidifficulty.AIDifficulty;
import com.example.tankgame.direction.Direction;
import com.example.tankgame.explosion.Explosion;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.missile.BasicMissile;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.AITank;
import com.example.tankgame.tank.PlayerTank;
import com.example.tankgame.tank.Tank;

public class GameObjectFactory {
    private final GameObjectManager gameObjectManager;

    public GameObjectFactory(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    public PlayerTank createPlayerTank(double x, double y) {
        return new PlayerTank(x, y, gameObjectManager);
    }

    public AITank createEnemyTank(double x, double y, Tank tank, AIDifficulty AIDifficulty) {
        return new AITank(x, y, tank, AIDifficulty,gameObjectManager);
    }

    public MedPack createMedPack(double x, double y) {
        return new MedPack(x, y);
    }

    public static Missile createBasicMissile(double x, double y, Direction direction) {
        Missile missile = new BasicMissile(x, y, direction);
        missile.setState(direction);
        return missile;
    }

    public Explosion createExplosion(double x, double y) {
        return new Explosion(x, y);
    }
}

