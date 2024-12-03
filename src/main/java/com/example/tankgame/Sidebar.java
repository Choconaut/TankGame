package com.example.tankgame;

import com.example.tankgame.gameobject.tank.PlayerTank;
import com.example.tankgame.gameobject.tank.team.Team;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 *  This class represents the Sidebar of the game which displays the
 *  player's health, number of enemies and allies.
 */
public class Sidebar extends VBox {
    private Tile healthTile;
    private HBox enemyBox;
    private HBox allyBox;

    private final Image enemyTankImage;
    private final Image allyTankImage;

    private final int maxTankIcons = 10; // Maximum number of tank icons to display

    public Sidebar(PlayerTank playerTank, Team allies, Team axis) {
        super(20);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #333;");
        this.setPrefWidth(250);

        // Load images
        enemyTankImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/tankgame/images/bTankU.png")));
        allyTankImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/tankgame/images/bTankU.png")));

        initializeComponents();
        bindProperties(playerTank, allies, axis);
    }

    private void initializeComponents() {
        healthTile = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_GAUGE)
                .title("Health")
                .textSize(Tile.TextSize.BIGGER)
                .unit("%")
                .maxValue(100)
                .threshold(40) // Threshold for color change
                .barColor(Tile.GREEN)
                .thresholdColor(Tile.RED)
                .animated(true)
                .prefSize(100,200)
                .build();

        // Enemy Tank Images
        Label enemyTitle = new Label("Enemies");
        enemyTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        enemyBox = new HBox(5);
        enemyBox.setAlignment(Pos.CENTER_LEFT);

        VBox enemyBoxContainer = new VBox(5, enemyTitle, enemyBox);

        // Ally Tank Images
        Label allyTitle = new Label("Allies");
        allyTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        allyBox = new HBox(5);
        allyBox.setAlignment(Pos.CENTER_LEFT);

        VBox allyBoxContainer = new VBox(5, allyTitle, allyBox);

        // Add components to Sidebar
        this.getChildren().addAll(healthTile, enemyBoxContainer, allyBoxContainer);
    }

    // Bind properties of player tank and teams to the Sidebar components
    private void bindProperties(PlayerTank playerTank, Team alliesTeam, Team enemiesTeam) {
        // Update Health Tile
        playerTank.healthProperty().addListener((observable, oldValue, newValue) -> {
            double healthPercentage = (newValue.doubleValue() / playerTank.getMaxHealth()) * 100;
            healthTile.setValue(healthPercentage);
        });

        // Set initial health
        healthTile.setValue((playerTank.getCurrentHealth() / (double) playerTank.getMaxHealth()) * 100);

        // Update Enemy Tank Images
        enemiesTeam.activeTankCount().addListener((observable, oldValue, newValue) -> {
            updateEnemyIcons(newValue.intValue());
        });

        // Set initial enemy count
        updateEnemyIcons(enemiesTeam.getActiveTankCount());

        // Update Ally Tank Images
        alliesTeam.activeTankCount().addListener((observable, oldValue, newValue) -> {
            int allyCount = newValue.intValue() - 1; // Exclude player tank
            updateAllyIcons(allyCount);
        });

        // Set initial ally count
        updateAllyIcons(alliesTeam.getActiveTankCount() - 1);
    }

    private void updateEnemyIcons(int enemyCount) {
        enemyBox.getChildren().clear();

        int iconsToShow = Math.min(enemyCount, maxTankIcons);

        for (int i = 0; i < iconsToShow; i++) {
            ImageView tankView = new ImageView(enemyTankImage);
            tankView.setFitWidth(30);
            tankView.setFitHeight(30);
            enemyBox.getChildren().add(tankView);
        }

        // Add label for additional enemies if any
        if (enemyCount > maxTankIcons) {
            Label moreLabel = new Label("+" + (enemyCount - maxTankIcons));
            moreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            enemyBox.getChildren().add(moreLabel);
        }
    }

    private void updateAllyIcons(int allyCount) {
        allyBox.getChildren().clear();

        int iconsToShow = Math.min(allyCount, maxTankIcons);

        for (int i = 0; i < iconsToShow; i++) {
            ImageView tankView = new ImageView(allyTankImage);
            tankView.setFitWidth(30);
            tankView.setFitHeight(30);
            allyBox.getChildren().add(tankView);
        }

        // Add label for additional allies if any
        if (allyCount > maxTankIcons) {
            Label moreLabel = new Label("+" + (allyCount - maxTankIcons));
            moreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            allyBox.getChildren().add(moreLabel);
        }
    }
}
