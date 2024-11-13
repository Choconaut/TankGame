package com.example.tankgame.powerup;

import com.example.tankgame.tank.Tank;

public class MedPack extends PowerUps {
    public MedPack(double x, double y) {
        super(x, y);
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/MedPack.gif";
    }

    @Override
    public void update() {

    }

    public void heal(Tank tank) {
        tank.setHealth(Math.min(tank.getHealth() + 60, 100));
        System.out.println("Tank healed! Health: " + tank.getHealth());
    }

    @Override
    public void handleCollision(Tank tank) {
        heal(tank);
        this.setActive(false);
    }

}
