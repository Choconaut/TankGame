package com.example.tankgame;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceManager {
    private static ResourceManager instance;
    private final Map<String, Image> imageCache = new HashMap<>();

    private ResourceManager() {}

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public Image getImage(String path) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
        }
        return imageCache.get(path);
    }
}
