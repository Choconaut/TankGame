package com.example.tankgame;

import com.example.tankgame.tank.EnemyTank;
import com.example.tankgame.tank.EnemyTankAI;
import com.example.tankgame.tank.PlayerTank;
import com.example.tankgame.tank.TankRenderer;
import com.example.tankgame.direction.Down;
import com.example.tankgame.direction.Left;
import com.example.tankgame.direction.Right;
import com.example.tankgame.direction.Up;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TankApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        PlayerTank playerTank = new PlayerTank(screenBounds.getWidth() / 2 - 25, screenBounds.getHeight() / 2 - 25);
        TankRenderer playerTankRenderer = new TankRenderer(playerTank);

        EnemyTank enemyTank = new EnemyTank(screenBounds.getWidth() / 6 - 25, screenBounds.getHeight() / 6 - 25);
        TankRenderer enemyTankRenderer = new TankRenderer(enemyTank);

        EnemyTankAI enemyTankAI = new EnemyTankAI(enemyTank, playerTank);

        root.getChildren().add(playerTankRenderer.getImageView());
        root.getChildren().add(enemyTankRenderer.getImageView());

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tank Game");
        primaryStage.show();

        // Input handling
        //TODO: Implement this into a separate class,
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
                enemyTank.update();
                enemyTankRenderer.update();
                enemyTankAI.update();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
