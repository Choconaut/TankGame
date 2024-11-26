module com.example.tankgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.tankgame to javafx.fxml;
    exports com.example.tankgame;
    exports com.example.tankgame.gameobject.tank;
    exports com.example.tankgame.gameobject.tank.team;
    exports com.example.tankgame.direction;
    exports com.example.tankgame.gameobject.missile;
    exports com.example.tankgame.gameobject.powerup;
    exports com.example.tankgame.gameobject.explosion;
    exports com.example.tankgame.gameobject;
    exports com.example.tankgame.aidifficulty;
    exports com.example.tankgame.gameobject.obstacle;
    opens com.example.tankgame.gameobject.tank to javafx.fxml;
    opens com.example.tankgame.gameobject to javafx.fxml;
}