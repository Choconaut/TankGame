package com.example.tankgame.direction;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.gameobject.missile.Missile;
import com.example.tankgame.gameobject.tank.Tank;

public class Right implements Direction {

    @Override
    public void move(GameObject object, double speed) {
        object.setX(object.getX() + speed);
    }

    @Override
    public String getImagePath(GameObject object) {
        if(object instanceof Tank)
            return "/com/example/tankgame/images/bTankR.png";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileR.gif";
        return null;
    }

    @Override
    public int getOffsetX() {
        return 40;
    }

    @Override
    public int getOffsetY() {
        return 24;
    }
}
