package com.example.tankgame;

import com.example.tankgame.aidifficulty.HardDifficulty;
import com.example.tankgame.aidifficulty.MediumDifficulty;
import com.example.tankgame.gameobject.GameObjectFactory;
import com.example.tankgame.gameobject.GameObjectManager;
import com.example.tankgame.gameobject.tank.*;
import com.example.tankgame.gameobject.tank.team.TeamManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class TankApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create the game area as a Group
        Group gameArea = new Group();

        // Load and set the background image
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/tankgame/images/map1.png")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1200); // Adjusted width for game area
        backgroundImageView.setFitHeight(800);
        backgroundImageView.setPreserveRatio(false);

        gameArea.getChildren().add(backgroundImageView);

        // Place the game area in the center of the BorderPane
        root.setCenter(gameArea);

        Scene scene = new Scene(root, 1450, 800);

        // Update GameConstants with actual scene size
        GameConstants.gameWidth = 1200;
        GameConstants.gameHeight = scene.getHeight();

        // Create GameObjectManager, GameObjectFactory, TeamManager

        GameObjectManager gameObjectManager = new GameObjectManager(gameArea);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameObjectManager);
        TeamManager teamManager = new TeamManager();
        teamManager.createTeam("Allies"); // Own Team
        teamManager.createTeam("Axis"); // Enemy Team

        //Create GameObjects and add them to the GameObjectManager

        // Allies Team
        PlayerTank playerTank = gameObjectFactory.createPlayerTank(
                240, 700, // Starting position
                teamManager.getTeam("Allies")); // Own Team
        gameObjectManager.addGameObject(playerTank); // Add to GameObjectManager

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                250, 200,
                teamManager.getTeam("Allies"), // Own Team
                teamManager.getTeam("Axis"), // Enemy Team
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                220, 450,
                teamManager.getTeam("Allies"),
                teamManager.getTeam("Axis"),
                new MediumDifficulty()));

        // Axis Team
        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                850, 100,
                teamManager.getTeam("Axis"),
                teamManager.getTeam("Allies"),
                new MediumDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                920, 250,
                teamManager.getTeam("Axis"),
                teamManager.getTeam("Allies"),
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                1130, 350,
                teamManager.getTeam("Axis"),
                teamManager.getTeam("Allies"),
                new HardDifficulty()));

        gameObjectManager.addGameObject(gameObjectFactory.createAITank(
                1150, 450,
                teamManager.getTeam("Axis"),
                teamManager.getTeam("Allies"),
                new MediumDifficulty()));

        // PowerUps
        gameObjectManager.addGameObject(gameObjectFactory.createMedPack(1103, 660));

        // Obstacles

        gameObjectManager.addGameObject(gameObjectFactory.createBoulder(500, 250));

        // Wall for left-side of stairway
        gameObjectManager.addGameObject(gameObjectFactory.createInvisibleWall(0, 525, 315, 50));

        // Wall for right-side of stairway
        gameObjectManager.addGameObject(gameObjectFactory.createInvisibleWall(380, 525, 185, 50));

        // Wall for Corner 1 of downward path
        gameObjectManager.addGameObject(gameObjectFactory.createInvisibleWall(570, 563, 50, 50));

        // Wall for Corner 2 of downward path
        gameObjectManager.addGameObject(gameObjectFactory.createInvisibleWall(630, 613, 50, 50));

        // Wall for downward path
        gameObjectManager.addGameObject(gameObjectFactory.createInvisibleWall(685, 613, 5, 175));

        // Create the sidebar and pass the necessary references
        Sidebar sidebar = new Sidebar(playerTank, teamManager.getTeam("Allies"), teamManager.getTeam("Axis"));
        root.setRight(sidebar);

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
                } else {
                    teamManager.getTeam("Allies").updateActiveTankCount();
                    teamManager.getTeam("Axis").updateActiveTankCount();
                }
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        System.out.println("Game is starting...");
        launch(args);
    }
}
