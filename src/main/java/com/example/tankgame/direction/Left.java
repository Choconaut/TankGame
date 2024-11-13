package com.example.tankgame.direction;

import com.example.tankgame.GameObject;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.tank.Tank;

public class Left implements Direction {

    @Override
    public void move(GameObject object, double speed) {
        object.setX(object.getX() - speed);
    }

    @Override
    public String getImagePath(GameObject object) {
        if(object instanceof Tank)
            return "/com/example/tankgame/images/bTankL.png";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileL.gif";
        return null;
    }

    @Override
    public int getOffsetX() {
        return 0;
    }

    @Override
    public int getOffsetY() {
        return 24;
    }
}
