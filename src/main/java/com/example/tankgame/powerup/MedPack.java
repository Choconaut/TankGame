package com.example.tankgame.powerup;

import com.example.tankgame.GameObject;
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
        tank.setHealth(tank.getHealth() + 60);
    }

    @Override
    public void handleCollision(GameObject obj2) {
        if (obj2 instanceof Tank) {
            heal((Tank) obj2);
            System.out.println(((Tank) obj2).getHealth());
        }
    }

}
