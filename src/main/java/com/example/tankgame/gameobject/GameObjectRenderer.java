package com.example.tankgame.gameobject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * GameObjectRenderer is a class that is responsible for rendering a GameObject.
 * It contains a GameObject and an ImageView. The GameObjectRenderer updates the
 * ImageView based on the GameObject's image path and position.
 */
public class GameObjectRenderer {
    private final GameObject gameObject;
    private final ImageView imageView;

    public GameObjectRenderer(GameObject gameObject) {
        this.gameObject = gameObject;
        this.imageView = new ImageView();
        updateImage();
        updatePosition();
    }

    public void update() {
        updateImage();
        updatePosition();
    }

    // Update the image of the ImageView based on the image path of the GameObject.
    private void updateImage() {
        String imagePath = gameObject.getImagePath();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        imageView.setImage(image);
    }

    public void updatePosition() {
        imageView.setX(gameObject.getX());
        imageView.setY(gameObject.getY());
    }

    public void remove() {
        imageView.setImage(null);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    // animation requires an array of image paths

}
