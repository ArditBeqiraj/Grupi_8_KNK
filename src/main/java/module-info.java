module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    exports test;
opens test to javafx.fxml;

    opens app to javafx.fxml;
    exports app;
}