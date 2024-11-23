package com.example.tankgame.gameobject.powerup;

import com.example.tankgame.gameobject.tank.Tank;

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

    @Override
    public void handleCollision(Tank tank) {
        applyEffect(tank);
        this.setActive(false);
    }

    // Heals the tank by 60 health points
    @Override
    public void applyEffect(Tank tank) {
        tank.setHealth(Math.min(tank.getHealth() + 60, 100));
        System.out.println("Tank healed! Health: " + tank.getHealth());
    }

}
