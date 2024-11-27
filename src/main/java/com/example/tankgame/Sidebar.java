package com.example.tankgame;

import com.example.tankgame.gameobject.tank.PlayerTank;
import com.example.tankgame.gameobject.tank.team.Team;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;

public class Sidebar extends VBox {
    private Label hpLabel;
    private ProgressBar hpBar;
    Label enemyCountLabel;
    Label allyCountLabel;

    public Sidebar(PlayerTank playerTank, Team allies, Team axis) {
        super(20); //Spacing between elements
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #333;"); // Background color for the sidebar
        this.setPrefWidth(200);

        // Initialize UI components
        initializeComponents();

        // Bind properties to update UI components
        bindProperties(playerTank, allies, axis);
    }

    private void initializeComponents() {
        // HP Label and ProgressBar
        Label hpTitle = new Label("Health");
        hpTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        hpLabel = new Label("100 / 100");
        hpLabel.setStyle("-fx-text-fill: white;");

        hpBar = new ProgressBar(1.0);
        hpBar.setPrefWidth(150);

        VBox hpBox = new VBox(5, hpTitle, hpBar, hpLabel);

        // Enemies Label
        enemyCountLabel = new Label("Enemies: 0");
        enemyCountLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        // Allies Label
        allyCountLabel = new Label("Allies: 0");
        allyCountLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        // Add elements to sidebar
        this.getChildren().addAll(hpBox, enemyCountLabel, allyCountLabel);
    }

    // Methods to update UI components
    public void updateHealth(int currentHealth, int maxHealth) {
        hpLabel.setText(currentHealth + " / " + maxHealth);
        hpBar.setProgress((double) currentHealth / maxHealth);
    }

    public void updateEnemies(int enemyCount) {
        enemyCountLabel.setText("Enemies: " + enemyCount);
    }

    public void updateAllies(int allyCount) {
        allyCountLabel.setText("Allies: " + allyCount);
    }

    private void bindProperties(PlayerTank playerTank, Team alliesTeam, Team enemiesTeam) {
        // Bind health
        playerTank.healthProperty().addListener((observable, oldValue, newValue) -> {
            int currentHealth = newValue.intValue();
            int maxHealth = 100; // Assuming max health is 100
            updateHealth(currentHealth, maxHealth);
        });

        // Set initial health
        updateHealth(playerTank.getHealth(), 100);

        // Bind allies count
        alliesTeam.activeTankCount().addListener((observable, oldValue, newValue) -> {
            int allyCount = newValue.intValue() - 1; // Exclude player tank
            updateAllies(allyCount);
        });

        // Set initial allies count
        updateAllies(alliesTeam.getActiveTankCount() - 1);

        // Bind enemies count
        enemiesTeam.activeTankCount().addListener((observable, oldValue, newValue) -> {
            int enemyCount = newValue.intValue();
            updateEnemies(enemyCount);
        });

        // Set initial enemies count
        updateEnemies(enemiesTeam.getActiveTankCount());
    }

}
