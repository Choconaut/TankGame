package com.example.tankgame.gameobject.powerup;

import com.example.tankgame.gameobject.tank.Tank;

/**
 * MedPack class is a subclass of PowerUps class. When a tank collides with a MedPack object,
 * the tank's currentHealth is increased by 60 currentHealth points.
 */
public class MedPack extends PowerUps {
    public MedPack(double x, double y) {
        super(x, y);
        width = 38;
        height = 40;
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/BiggerMedPack.gif";
    }

    @Override
    public void update() {

    }

    // Only tanks can interact with MedPack objects
    @Override
    public void handleCollision(Tank tank) {
        applyEffect(tank);
        this.setActive(false);
    }

    // Heals the tank by 60 currentHealth points, but the tank's currentHealth cannot exceed 100
    @Override
    public void applyEffect(Tank tank) {
        tank.setCurrentHealth(Math.min(tank.getCurrentHealth() + 60, 100));
        System.out.println("Tank healed! Health: " + tank.getCurrentHealth());
    }

}
