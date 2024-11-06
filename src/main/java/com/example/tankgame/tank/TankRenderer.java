package com.example.tankgame.tank;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class TankRenderer {
    private Tank tank;
    private ImageView imageView;

    public TankRenderer(Tank tank) {
        this.tank = tank;
        this.imageView = new ImageView();
        updateImage();
        updatePosition();
    }

    public void update() {
        updateImage();
        updatePosition();
    }

    private void updateImage() {
        String imagePath = tank.getState().getImagePath();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        imageView.setImage(image);
    }

    public void updatePosition() {
        imageView.setX(tank.getX());
        imageView.setY(tank.getY());
    }

    public ImageView getImageView() {
        return imageView;
    }
}
