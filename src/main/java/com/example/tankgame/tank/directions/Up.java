package com.example.tankgame.tank.directions;

import com.example.tankgame.tank.Tank;
import com.example.tankgame.tank.TankDirection;

public class Up implements TankDirection {

    @Override
    public void move(Tank tank) {
        tank.setY(tank.getY() - tank.getSpeed());
    }

    @Override
    public String getImagePath() {
        return "/com/example/tankgame/images/tankU.gif";
    }
}
