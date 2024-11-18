package com.example.tankgame.gameobject;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages a list of game objects and their corresponding renderers.
 * It handles adding, removing, and updating game objects and their images.
 */
public class GameObjectManager {
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final Map<GameObject, GameObjectRenderer> renderers = new HashMap<>();
    private final Group root;

    /**
     * Constructs a GameObjectManager with the specified root group for rendering.
     *
     * @param root the root group for rendering game objects
     */
    public GameObjectManager(Group root) {
        this.root = root;
    }

    /**
     * Adds a game object to the manager and its renderer to the root group.
     *
     * @param gameObject the game object to add
     */
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

    /**
     * Updates all game objects and their renderers based on their active status.
     * Removes inactive game objects after updating.
     */
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

    /**
     * Returns a list of all game objects managed by this manager.
     */
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }
}
