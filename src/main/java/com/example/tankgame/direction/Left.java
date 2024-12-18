package com.example.tankgame.direction;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.gameobject.missile.Missile;
import com.example.tankgame.gameobject.tank.Tank;

public class Left implements Direction {

    // Move the object left by the given speed - x coordinate
    @Override
    public void move(GameObject object, double speed) {
        object.setX(object.getX() - speed);
    }

    // Return the image path of the object based on instance of the object
    @Override
    public String getImagePath(GameObject object) {
        if(object instanceof Tank)
            return "/com/example/tankgame/images/bTankL.png";
        else if (object instanceof Missile)
            return "/com/example/tankgame/images/missileL.gif";
        return null;
    }

    // Return the x coordinate offset, currently used only for tank to have missiles come out of the nozzle of tank
    @Override
    public int getOffsetX() {
        return -10;
    }

    // Return the y coordinate offset, currently used only for tank to have missiles come out of the nozzle of tank
    @Override
    public int getOffsetY() {
        return 24;
    }
}
