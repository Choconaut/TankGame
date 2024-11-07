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

public class TankApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight(), Color.gray(0.2));

        //Game objects

        MissileManager missileManager = new MissileManager(root);

        GameObjectFactory gameObjectFactory = new GameObjectFactory(root);

        PlayerTank playerTank = gameObjectFactory.createPlayerTank(screenBounds.getWidth() / 2 - 25, screenBounds.getHeight() / 2 - 25, missileManager);
        EnemyTank enemyTank = gameObjectFactory.createEnemyTank(screenBounds.getWidth() / 6 - 25, screenBounds.getHeight() / 6 - 25, missileManager);
        EnemyTank enemyTank2 = gameObjectFactory.createEnemyTank(screenBounds.getWidth() - 75, screenBounds.getHeight() / 6 - 25, missileManager);
        MedPack medPack = gameObjectFactory.createMedPack(100, 100);
        EnemyTankAI enemyTankAI = new EnemyTankAI(enemyTank, playerTank);
        EnemyTankAI enemyTankAI2 = new EnemyTankAI(enemyTank2, playerTank);

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


//        PlayerTank playerTank = new PlayerTank(screenBounds.getWidth() / 2 - 25, screenBounds.getHeight() / 2 - 25, missileManager);
//        TankRenderer playerTankRenderer = new TankRenderer(playerTank);
//
//        EnemyTank enemyTank = new EnemyTank(screenBounds.getWidth() / 6 - 25, screenBounds.getHeight() / 6 - 25, missileManager);
//        TankRenderer enemyTankRenderer = new TankRenderer(enemyTank);
//        EnemyTankAI enemyTankAI = new EnemyTankAI(enemyTank, playerTank);
//
//        EnemyTank enemyTank2 = new EnemyTank(screenBounds.getWidth() - 75, screenBounds.getHeight() / 6 - 25, missileManager);
//        TankRenderer enemyTankRenderer2 = new TankRenderer(enemyTank2);
//        EnemyTankAI enemyTankAI2 = new EnemyTankAI(enemyTank2, playerTank);

//        MedPack medPack = new MedPack(100, 100);
//        PowerUpRenderer medPackRenderer = new PowerUpRenderer(medPack);
//        root.getChildren().add(medPackRenderer.getImageView());

//        root.getChildren().add(playerTankRenderer.getImageView());
//        root.getChildren().add(enemyTankRenderer.getImageView());
//        root.getChildren().add(enemyTankRenderer2.getImageView());

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

                missileManager.update(screenBounds.getWidth(), screenBounds.getHeight());
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
