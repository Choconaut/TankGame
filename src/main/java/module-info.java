module com.example.tankgame {
    requires javafx.controls;

    requires eu.hansolo.tilesfx;
    requires javafx.graphics;

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
}