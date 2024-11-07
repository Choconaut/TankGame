package com.example.tankgame.missile;

import com.example.tankgame.GameObjectRenderer;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MissileManager {
    private final List<GameObjectRenderer> missiles = new ArrayList<>();
    private final Group root;

    public MissileManager(Group root) {
        this.root = root;
    }

    public void addMissile(Missile missile) {
        GameObjectRenderer missileRenderer = new GameObjectRenderer(missile);
        missiles.add(missileRenderer);
        root.getChildren().add(missileRenderer.getImageView());
    }

    public void update(double screenWidth, double screenHeight) {
        Iterator<GameObjectRenderer> iterator = missiles.iterator();
        while (iterator.hasNext()) {
            GameObjectRenderer missileRenderer = iterator.next();
            Missile missile = (Missile) missileRenderer.getGameObject();

            missile.move();

            missileRenderer.update();

            if (missile.isOutOfBounds(screenWidth, screenHeight)) {
                root.getChildren().remove(missileRenderer.getImageView());
                iterator.remove();
            }
        }
    }
}
