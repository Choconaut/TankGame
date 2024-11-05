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
    exports com.example.tankgame.tank;
    opens com.example.tankgame.tank to javafx.fxml;
}