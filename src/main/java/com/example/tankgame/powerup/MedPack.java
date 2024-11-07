package com.example.tankgame.powerup;

import com.example.tankgame.tank.Tank;

public class MedPack extends PowerUps {
    public MedPack(double x, double y) {
        super(x, y);
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/MedPack.gif"; //TODO: Change to medpack image
    }

    @Override
    public void update() {

    }

    public void heal(Tank tank) {
        tank.setHealth(tank.getHealth() + 60);
    }


}
