package com.example.tankgame;

import com.example.tankgame.missile.MissileManager;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TankApp extends Application {

    private final List<GameObject> gameObjects = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Scene scene = new Scene(root, 800, 800, Color.gray(0.2));

        //Game objects

        MissileManager missileManager = new MissileManager(root);

        //Create GameObjects
        GameObjectFactory gameObjectFactory = new GameObjectFactory(root);

        PlayerTank playerTank = gameObjectFactory.createPlayerTank(675, 675, missileManager);
        EnemyTank enemyTank = gameObjectFactory.createEnemyTank(100, 100, missileManager);
        EnemyTank enemyTank2 = gameObjectFactory.createEnemyTank(100, 700, missileManager);
        MedPack medPack = gameObjectFactory.createMedPack(100, 100);
        EnemyTankAI enemyTankAI = new EnemyTankAI(enemyTank, playerTank);
        EnemyTankAI enemyTankAI2 = new EnemyTankAI(enemyTank2, playerTank);

        //Add GameObjects to list
        gameObjects.add(playerTank);
        gameObjects.add(enemyTank);
        gameObjects.add(enemyTank2);
        gameObjects.add(medPack);

        //Create Renderers
        GameObjectRenderer playerTankRenderer = new GameObjectRenderer(playerTank);
        GameObjectRenderer enemyTankRenderer = new GameObjectRenderer(enemyTank);
        GameObjectRenderer enemyTankRenderer2 = new GameObjectRenderer(enemyTank2);
        GameObjectRenderer medPackRenderer = new GameObjectRenderer(medPack);

        root.getChildren().addAll(
          playerTankRenderer.getImageView(),
          enemyTankRenderer.getImageView(),
          enemyTankRenderer2.getImageView(),
          medPackRenderer.getImageView()
        );

        // Handle Collisions
        CollisionDetector collisionDetector = new CollisionDetector();

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tank Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        TankController tankController = new TankController(scene, playerTank);

        // Game loop to update game logic and rendering
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerTank.update();
                playerTankRenderer.update();

                enemyTank.update();
                enemyTankRenderer.update();
                enemyTankAI.update();

                enemyTank2.update();
                enemyTankRenderer2.update();
                enemyTankAI2.update();

                medPack.update();

                missileManager.update(800,  800);

                gameObjects.clear();
                gameObjects.add(playerTank);
                gameObjects.add(enemyTank);
                gameObjects.add(enemyTank2);
                gameObjects.add(medPack);

                // Add all active missiles to the gameObjects list
                gameObjects.addAll(missileManager.getMissiles());

                collisionDetector.detectCollision(gameObjects);

                missileManager.removeInactiveMissiles();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
