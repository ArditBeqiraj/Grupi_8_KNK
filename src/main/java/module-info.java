module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    exports test;
opens test to javafx.fxml;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}