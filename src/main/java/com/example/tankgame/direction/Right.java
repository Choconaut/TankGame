package com.example.tankgame.direction;

import com.example.tankgame.tank.Tank;
import com.example.tankgame.tank.TankDirection;

public class Right implements TankDirection {
    @Override
    public void move(Tank tank) {
        tank.setX(tank.getX() + tank.getSpeed());
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/tankR.gif";
    }
}
