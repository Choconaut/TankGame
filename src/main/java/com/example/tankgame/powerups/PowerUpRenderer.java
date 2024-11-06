package com.example.tankgame.powerups;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PowerUpRenderer {
    private final PowerUps powerUps;
    private final ImageView imageView;

    public PowerUpRenderer(PowerUps powerUps) {
        this.powerUps = powerUps;
        this.imageView = new ImageView();
        updateImage();
        updatePosition();
    }

    public void update() {
        updateImage();
        updatePosition();
    }

    private void updateImage() {
        String imagePath = powerUps.getImagePath();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        imageView.setImage(image);
    }

    private void updatePosition() {
        imageView.setX(powerUps.getX());
        imageView.setY(powerUps.getY());
    }

    public ImageView getImageView() { return imageView; }

}
