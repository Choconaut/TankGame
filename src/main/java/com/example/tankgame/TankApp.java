package com.example.tankgame;

import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TankApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Color backgroundColor = Color.rgb(139, 99, 69);

        Scene scene = new Scene(root, 800, 800, backgroundColor);

        GameObjectManager gameObjectManager = new GameObjectManager(root);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameObjectManager);

        //Create GameObjects
        PlayerTank playerTank = gameObjectFactory.createPlayerTank(375, 675);
        gameObjectManager.addGameObject(playerTank);

        gameObjectManager.addGameObject(gameObjectFactory.createEnemyTank(100, 100));

        EnemyTank enemyTank2 = gameObjectFactory.createEnemyTank(700, 100);
        gameObjectManager.addGameObject(enemyTank2);

        MedPack medPack = gameObjectFactory.createMedPack(100, 100);
        gameObjectManager.addGameObject(medPack);

        EnemyTankAI enemyTankAI = new EnemyTankAI(enemyTank, playerTank);
        EnemyTankAI enemyTankAI2 = new EnemyTankAI(enemyTank2, playerTank);

        // Handle Collisions
        CollisionDetector collisionDetector = new CollisionDetector();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Tank Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        new TankController(scene, playerTank);

        // Game loop to update game logic and rendering
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameObjectManager.updateAll();
                collisionDetector.detectCollision(gameObjectManager.getGameObjects());
                enemyTankAI.update();
                enemyTankAI2.update();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
