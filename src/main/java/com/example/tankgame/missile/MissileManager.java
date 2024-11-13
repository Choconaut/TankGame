package com.example.tankgame.missile;

import com.example.tankgame.GameObjectRenderer;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MissileManager {
    private final List<GameObjectRenderer> missileRenderers = new ArrayList<>();
    private final List<Missile> missiles = new ArrayList<>();
    private final Group root;

    public MissileManager(Group root) {
        this.root = root;
    }

    public void addMissile(Missile missile) {
        GameObjectRenderer missileRenderer = new GameObjectRenderer(missile);
        missileRenderers.add(missileRenderer);
        missiles.add(missile);
        root.getChildren().add(missileRenderer.getImageView());
    }

    public void update(double screenWidth, double screenHeight) {
        Iterator<GameObjectRenderer> rendererIterator = missileRenderers.iterator();
        Iterator<Missile> missileIterator = missiles.iterator();

        while (rendererIterator.hasNext() && missileIterator.hasNext()) {
            GameObjectRenderer missileRenderer = rendererIterator.next();
            Missile missile = missileIterator.next();

            missile.move();
            missileRenderer.update();

            if (missile.isOutOfBounds(screenWidth, screenHeight)) {
                root.getChildren().remove(missileRenderer.getImageView());
                rendererIterator.remove();
                missileIterator.remove();
            }
        }
    }

    public void removeInactiveMissiles() {
        Iterator<Missile> missileIterator = missiles.iterator();
        Iterator<GameObjectRenderer> rendererIterator = missileRenderers.iterator();

        while (missileIterator.hasNext() && rendererIterator.hasNext()) {
            Missile missile = missileIterator.next();
            GameObjectRenderer renderer = rendererIterator.next();

            if (!missile.isActive()) {
                root.getChildren().remove(renderer.getImageView());
                missileIterator.remove();
                rendererIterator.remove();
            }
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

}
