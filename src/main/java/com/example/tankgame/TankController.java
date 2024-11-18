package com.example.tankgame;

import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import javafx.scene.Scene;
import com.example.tankgame.tank.PlayerTank;

/**
 * TankController class is responsible for controlling the player tank.
 */
public class TankController {

    public TankController(Scene scene, PlayerTank playerTank) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    playerTank.setState(new Up());
                    playerTank.move();
                    break;
                case DOWN:
                    playerTank.setState(new Down());
                    playerTank.move();
                    break;
                case LEFT:
                    playerTank.setState(new Left());
                    playerTank.move();
                    break;
                case RIGHT:
                    playerTank.setState(new Right());
                    playerTank.move();
                    break;
                case SPACE:
                    playerTank.fire();
                    break;
            }
        });
    }

}