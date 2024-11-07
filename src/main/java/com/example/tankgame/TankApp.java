package com.example.tankgame;

import com.example.tankgame.missile.MissileManager;
import com.example.tankgame.powerup.MedPack;
import com.example.tankgame.tank.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        List<GameObjectRenderer> renderers = new ArrayList<>();
        CollisionManager collisionManager = new CollisionManager(renderers, root);

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

        //List of renderers used to detect collisions
        renderers.add(playerTankRenderer);
        renderers.add(enemyTankRenderer);
        renderers.add(enemyTankRenderer2);
        renderers.add(medPackRenderer);

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

                for (GameObjectRenderer renderer : renderers) {
                    renderer.getGameObject().update();
                }

                collisionManager.checkCollisions();

                missileManager.update(screenBounds.getWidth(), screenBounds.getHeight(), renderers);
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
