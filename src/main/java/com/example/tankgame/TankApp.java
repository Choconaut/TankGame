package com.example.tankgame;

import com.example.tankgame.aidifficulty.HardDifficulty;
import com.example.tankgame.gameobject.GameObjectFactory;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.gameobject.tank.*;
import com.example.tankgame.gameobject.tank.team.TeamManager;
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

        GameObjectManager gameObjectManager = new GameObjectManager(root);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameObjectManager);
        TeamManager teamManager = new TeamManager();
        teamManager.createTeam("Allies"); // Own Team
        teamManager.createTeam("Axis"); // Enemy Team

        //Create GameObjects and add them to the GameObjectManager

        // Allies
        PlayerTank playerTank = gameObjectFactory.createPlayerTank(
                100, 450, // Starting position
                teamManager.getTeam("Allies")); // Own Team
        gameObjectManager.addGameObject(playerTank); // Add to GameObjectManager

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                150, 200,
                teamManager.getTeam("Allies"), // Own Team
                teamManager.getTeam("Axis"), // Enemy Team
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                120, 700,
                teamManager.getTeam("Allies"), // Own Team
                teamManager.getTeam("Axis"), // Enemy Team
                new HardDifficulty()));

        // Axis
        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                900, 50,
                teamManager.getTeam("Axis"), // Own Team
                teamManager.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                920, 250,
                teamManager.getTeam("Axis"), // Own Team
                teamManager.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                960, 600,
                teamManager.getTeam("Axis"), // Own Team
                teamManager.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                950, 700,
                teamManager.getTeam("Axis"), // Own Team
                teamManager.getTeam("Allies"), // Enemy Team
                new HardDifficulty()));

        // PowerUps
        gameObjectManager.addGameObject(gameObjectFactory.createMedPack(500, 450));

        // Walls
        gameObjectManager.addGameObject(gameObjectFactory.createSquareWall(300, 300));


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
                if (!playerTank.isActive()) {
                    stop();
                }

                gameObjectManager.updateAll();
                collisionDetector.detectCollision(gameObjectManager.getGameObjects());
                teamManager.updateTeams();

                if (teamManager.checkLastTeamStanding()) {
                    stop();
                }

            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
