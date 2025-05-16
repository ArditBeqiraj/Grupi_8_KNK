module com.example.demo {
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;

    exports controllers;
    exports test;
    exports app;
    exports Repository;
    exports services;
    exports utils;

    opens model.Pacientet to javafx.base;
    opens model.Perdoruesi to javafx.base;
    opens test to javafx.fxml;
    opens app to javafx.fxml;
    opens controllers to javafx.fxml;
    opens Repository to javafx.fxml;
    opens services to javafx.fxml;
    opens utils to javafx.fxml;
}