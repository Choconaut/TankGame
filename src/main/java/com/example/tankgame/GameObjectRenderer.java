package com.example.tankgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameObjectRenderer {
    private GameObject gameObject;
    private ImageView imageView;

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
}
