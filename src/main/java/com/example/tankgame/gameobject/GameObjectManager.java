package com.example.tankgame.gameobject;

import com.example.tankgame.GameObject;
import com.example.tankgame.GameObjectRenderer;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObjectManager {
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final Map<GameObject, GameObjectRenderer> renderers = new HashMap<>();
    private final Group root;

    public GameObjectManager(Group root) {
        this.root = root;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        GameObjectRenderer renderer = new GameObjectRenderer(gameObject);
        renderers.put(gameObject, renderer);
        root.getChildren().add(renderer.getImageView());
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
        GameObjectRenderer renderer = renderers.remove(gameObject);
        if (renderer != null) {
            root.getChildren().remove(renderer.getImageView());
        }
    }

    public void updateAll() {
        // List to collect inactive objects
        List<GameObject> toRemove = new ArrayList<>();

        // Update game objects and their renderers
        for (GameObject obj : gameObjects) {
            if (obj.isActive()) {
                obj.update();
                renderers.get(obj).update();
            } else {
                toRemove.add(obj); // Mark for removal
            }
        }

        // Remove all inactive objects after iteration
        for (GameObject obj : toRemove) {
            removeGameObject(obj); // Automatically removes renderers
            System.out.println("Removing inactive object: " + obj);
        }
    }


    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }
}
