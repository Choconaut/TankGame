package com.example.tankgame.direction;

import com.example.tankgame.GameObject;
import com.example.tankgame.missile.Missile;
import com.example.tankgame.tank.Tank;

public class Right implements Direction {

    @Override
    public void move(GameObject object, double speed) {
        object.setX(object.getX() + speed);
    }

    @Override
    public String getImagePath(GameObject object) {
        if(object instanceof Tank)
            return "/com/example/tankgame/images/tankR.gif";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileR.gif";
        return null;
    }
}
