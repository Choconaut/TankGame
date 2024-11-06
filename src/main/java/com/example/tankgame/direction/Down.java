package com.example.tankgame.direction;

import com.example.tankgame.tank.Tank;
import com.example.tankgame.tank.TankDirection;

public class Down implements TankDirection {

    @Override
    public void move(Tank tank) {
        tank.setY(tank.getY() + tank.getSpeed());
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/tankD.gif";
    }
}
