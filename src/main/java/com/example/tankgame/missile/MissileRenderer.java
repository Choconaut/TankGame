package com.example.tankgame.missile;

import com.example.tankgame.ResourceManager;
import javafx.scene.image.ImageView;

public class MissileRenderer {
    private Missile missile;
    private ImageView imageView;

    public MissileRenderer(Missile missile) {
        this.missile = missile;
        this.imageView = new ImageView(ResourceManager.getInstance().getImage(missile.getState().getImagePath(missile)));
        updateImage();
        updatePosition();
    }

    public void update() {
        missile.move();
        updatePosition();
    }

    private void updateImage() {
        String imagePath = missile.getState().getImagePath(missile);
        imageView.setImage(ResourceManager.getInstance().getImage(imagePath));
    }

    public void updatePosition() {
        imageView.setX(missile.getX());
        imageView.setY(missile.getY());
    }

    public ImageView getImageView() { return imageView; }

    public Missile getMissile() { return missile; }
}
