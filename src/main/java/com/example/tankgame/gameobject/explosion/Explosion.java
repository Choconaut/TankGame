package com.example.tankgame.gameobject.explosion;

import com.example.tankgame.gameobject.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.Objects;

public class Explosion extends GameObject {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private int totalFrames;
    private int currentFrameIndex = 0;
    private long lastFrameTime;
    private boolean animationFinished = false;

    public Explosion(double x, double y) {
        super(x, y);
        initializeSpriteSheet();
        this.lastFrameTime = System.nanoTime();
    }

    private void initializeSpriteSheet() {
        String spriteSheetPath = "/com/example/tankgame/images/explosion_spritesheet.png";
        spriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(spriteSheetPath)));
        totalFrames = 8; // Amount of frames in the sprite sheet
        frameWidth = (int) spriteSheet.getWidth() / totalFrames;
        frameHeight = (int) spriteSheet.getHeight();
    }

    @Override
    public void update() {
        long now = System.nanoTime();
        // Duration per frame in nanoseconds
        long frameDuration = 100_000_000;
        if (now - lastFrameTime >= frameDuration) {
            lastFrameTime = now;
            currentFrameIndex++;
            if (currentFrameIndex >= totalFrames) {
                this.setActive(false); // Animation finished
                animationFinished = true;
            }
        }
    }

    @Override
    public String getImagePath() {
        // Not used when using sprite sheets
        return null;
    }

    public Image getCurrentFrame() {
        if (animationFinished) {
            return null;
        }
        PixelReader reader = spriteSheet.getPixelReader();
        int x = currentFrameIndex * frameWidth;
        return new WritableImage(reader, x, 0, frameWidth, frameHeight);
    }
}
