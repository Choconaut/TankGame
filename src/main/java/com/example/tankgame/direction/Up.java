package com.example.tankgame.direction;

import com.example.tankgame.GameObject;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.tank.Tank;

public class Up implements Direction {

    @Override
    public void move(GameObject object, double speed) {
        object.setY(object.getY() - speed);
    }

    @Override
    public String getImagePath(GameObject object) {
        if(object instanceof Tank)
            return "/com/example/tankgame/images/tankU.gif";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileU.gif";
        return null;
    }
}
