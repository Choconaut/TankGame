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
            return "/com/example/tankgame/images/tankL.gif";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileL.gif";
        return null;
    }
}
