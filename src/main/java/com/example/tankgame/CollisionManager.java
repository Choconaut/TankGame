package com.example.tankgame;

import javafx.geometry.Bounds;
import javafx.scene.Group;

import java.util.List;

public class CollisionManager {
    private List<GameObjectRenderer> renderers;
    private Group root;

    public CollisionManager(List<GameObjectRenderer> renderers, Group root) {
        this.renderers = renderers;
        this.root = root;
    }

    //Currently repeats the same collision check for each pair of objects
    public void checkCollisions() {
        int n = renderers.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                GameObjectRenderer renderer1 = renderers.get(i);
                GameObjectRenderer renderer2 = renderers.get(j);

                if(renderer1.getGameObject().getClass() == renderer2.getGameObject().getClass()) {
                    break;
                }

                GameObject obj1 = renderer1.getGameObject();
                GameObject obj2 = renderer2.getGameObject();

                Bounds bounds1 = obj1.getBounds(renderer1);
                Bounds bounds2 = obj2.getBounds(renderer2);

                if (bounds1.intersects(bounds2)) {
                    // Dispatch collision handling
                    //Collision repeats itself for each pair of objects + Multiple times per collision
                    obj1.handleCollision(obj2);
                    obj2.handleCollision(obj1);
                }
            }
        }
    }
}