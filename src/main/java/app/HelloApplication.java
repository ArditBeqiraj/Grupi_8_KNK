package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Perdoruesi.Perdoruesi;
import services.PasswordHasher;
import services.PerdoruesiService;

import java.io.IOException;
import java.sql.Timestamp;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.navigate(stage, Navigator.ADMIN_DASHBOARD, "Admin Dahboard");
    }

//    public static void main(String[] args) {
//        String salt = PasswordHasher.generateSalt();
//        Perdoruesi perdoruesi = new Perdoruesi(230756101, "Arbnor", "Tasholli",
//                "noltasholli@gmail.com", "044123123", "Admin", salt,
//                PasswordHasher.generateSaltedHash("arbnori123", salt), new Timestamp(System.currentTimeMillis()));
//
//        PerdoruesiService perdoruesiService = new PerdoruesiService();
//        perdoruesiService.create(perdoruesi);
//    }
}