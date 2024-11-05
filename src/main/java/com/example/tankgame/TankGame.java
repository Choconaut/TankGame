package com.example.tankgame;

import com.example.tankgame.tank.PlayerTank;
import com.example.tankgame.tank.TankRenderer;
import com.example.tankgame.tank.directions.Down;
import com.example.tankgame.tank.directions.Left;
import com.example.tankgame.tank.directions.Right;
import com.example.tankgame.tank.directions.Up;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TankGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        PlayerTank playerTank = new PlayerTank(screenBounds.getWidth() / 2 - 25, screenBounds.getHeight() / 2 - 25);
        TankRenderer playerTankRenderer = new TankRenderer(playerTank);

        root.getChildren().add(playerTankRenderer.getImageView());

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tank Game");
        primaryStage.show();

        // Input handling
        //TODO: Implement this into a separate class, stragetgy pattern
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
                    // Implement firing logic
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Tank Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Game loop to update game logic and rendering
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerTank.update();
                playerTankRenderer.update();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
