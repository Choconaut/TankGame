package com.example.tankgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TankController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}