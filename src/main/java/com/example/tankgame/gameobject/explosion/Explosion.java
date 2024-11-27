package com.example.tankgame.gameobject.explosion;

import com.example.tankgame.gameobject.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.Objects;

/**
 * Explosion class that handles the explosion animation. Explosions are created when a tank or a bullet is destroyed.
 *
 * The explosion animation is handled by a sprite sheet. The sprite sheet contains 8 frames of the explosion animation.
 * The sprite sheet is loaded and the current frame is updated every 100 milliseconds.
 *
 * Explosions do not interact with other game objects. They are only drawn on the screen.
 */
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

    // Load the sprite sheet and set the frame width and height
    private void initializeSpriteSheet() {
        String spriteSheetPath = "/com/example/tankgame/images/explosion_spritesheet.png";
        spriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(spriteSheetPath)));
        totalFrames = 8; // Amount of frames in the sprite sheet
        frameWidth = (int) spriteSheet.getWidth() / totalFrames;
        frameHeight = (int) spriteSheet.getHeight();
    }

    // Update the current frame index
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

    // Get the current frame from the sprite sheet
    public Image getCurrentFrame() {
        if (animationFinished) {
            return null;
        }
        PixelReader reader = spriteSheet.getPixelReader();
        int x = currentFrameIndex * frameWidth;
        return new WritableImage(reader, x, 0, frameWidth, frameHeight);
    }
}
