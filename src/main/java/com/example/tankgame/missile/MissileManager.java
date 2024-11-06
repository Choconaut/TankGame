package com.example.tankgame.missile;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MissileManager {
    private final List<MissileRenderer> missiles = new ArrayList<>();
    private final Group root;

    public MissileManager(Group root) {
        this.root = root;
    }

    public void addMissile(Missile missile) {
        MissileRenderer missileRenderer = new MissileRenderer(missile);
        missiles.add(missileRenderer);
        root.getChildren().add(missileRenderer.getImageView());
    }

    public void update(double screenWidth, double screenHeight) {
        Iterator<MissileRenderer> iterator = missiles.iterator();
        while (iterator.hasNext()) {
            MissileRenderer missileRenderer = iterator.next();
            missileRenderer.update();

            if (missileRenderer.getMissile().isOutOfBounds(screenWidth, screenHeight)) {
                root.getChildren().remove(missileRenderer.getImageView());
                iterator.remove();
            }
        }
    }
}
