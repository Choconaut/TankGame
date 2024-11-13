package com.example.tankgame;

import com.example.tankgame.direction.Direction;
import com.example.tankgame.missile.BasicMissile;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.missile.MissileManager;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.EnemyTank;
import com.example.tankgame.tank.PlayerTank;
import javafx.scene.Group;

public class GameObjectFactory {
    private Group root;

    public GameObjectFactory(Group root) {
        this.root = root;
    }

    public PlayerTank createPlayerTank(double x, double y, MissileManager missileManager) {
        return new PlayerTank(x, y, missileManager);
    }

    public EnemyTank createEnemyTank(double x, double y, MissileManager missileManager) {
        return new EnemyTank(x, y, missileManager);
    }

    public MedPack createMedPack(double x, double y) {
        return new MedPack(x, y);
    }

    public static Missile createBasicMissile(double x, double y, Direction direction) {
        Missile missile = new BasicMissile(x, y, direction);
        missile.setState(direction);
        return missile;
    }


}
