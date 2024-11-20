package com.example.tankgame;

import com.example.tankgame.aidifficulty.EasyDifficulty;
import com.example.tankgame.aidifficulty.HardDifficulty;
import com.example.tankgame.aidifficulty.MediumDifficulty;
import com.example.tankgame.gameobject.GameObjectFactory;
import com.example.tankgame.gameobject.GameObjectContainer;
import com.example.tankgame.gameobject.powerup.MedPack;
import com.example.tankgame.gameobject.tank.*;
import com.example.tankgame.gameobject.tank.team.TeamContainer;
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

        Scene scene = new Scene(root, 1200, 800, backgroundColor);

        GameObjectContainer gameObjectContainer = new GameObjectContainer(root);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameObjectContainer);
        TeamContainer teamContainer = new TeamContainer();
        teamContainer.createTeam("Allies"); // Own Team
        teamContainer.createTeam("Axis"); // Enemy Team

        //Create GameObjects and add them to the GameObjectManager

        // Allies
        PlayerTank playerTank = gameObjectFactory.createPlayerTank(
                100, 450, // Starting position
                teamContainer.getTeam("Allies")); // Own Team
        gameObjectContainer.addGameObject(playerTank); // Add to GameObjectManager

        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                150, 200,
                teamContainer.getTeam("Allies"), // Own Team
                teamContainer.getTeam("Axis"), // Enemy Team
                new HardDifficulty()));

        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                120, 700,
                teamContainer.getTeam("Allies"), // Own Team
                teamContainer.getTeam("Axis"), // Enemy Team
                new HardDifficulty()));

        // Axis
        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                900, 50,
                teamContainer.getTeam("Axis"), // Own Team
                teamContainer.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                920, 250,
                teamContainer.getTeam("Axis"), // Own Team
                teamContainer.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                960, 600,
                teamContainer.getTeam("Axis"), // Own Team
                teamContainer.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectContainer.addGameObject(gameObjectFactory.createAITank(
                950, 700,
                teamContainer.getTeam("Axis"), // Own Team
                teamContainer.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));


        MedPack medPack = gameObjectFactory.createMedPack(100, 100);
        gameObjectContainer.addGameObject(medPack);

        // Handle Collisions
        CollisionDetector collisionDetector = new CollisionDetector();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Tank Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        new TankController(scene, playerTank);

        // Game loop to execute game logic and rendering
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (teamContainer.getTeam("Axis").getTeam().isEmpty()) {
                    System.out.println("Allies Win!");
                    stop();
                } else if (teamContainer.getTeam("Allies").getTeam().isEmpty()) {
                    System.out.println("Axis Win!");
                    stop();
                }
                if (!playerTank.isActive()) {
                    stop();
                }

                gameObjectContainer.updateAll();
                collisionDetector.detectCollision(gameObjectContainer.getGameObjects());
                teamContainer.updateTeams();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
