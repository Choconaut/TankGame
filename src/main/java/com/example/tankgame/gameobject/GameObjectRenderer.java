package com.example.tankgame.gameobject;

import com.example.tankgame.gameobject.explosion.Explosion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private void updateImage() {
        if (gameObject instanceof Explosion) {
            Explosion explosion = (Explosion) gameObject;
            Image frame = explosion.getCurrentFrame();
            imageView.setImage(frame);
        } else {
            String imagePath = gameObject.getImagePath();
            if (imagePath != null) {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imageView.setImage(image);
            }
        }
    }

    private void updatePosition() {
        imageView.setX(gameObject.getX());
        imageView.setY(gameObject.getY());
    }

    public ImageView getImageView() {
        return imageView;
    }
}
