package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Perdoruesi.Perdoruesi;
import services.PasswordHasher;
import services.PerdoruesiService;
import services.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.navigate(stage, Navigator.LOGIN_PAGE, "Admin Dahboard");
    }

//    public static void main(String[] args) {
//        String salt = PasswordHasher.generateSalt();
//        String saltedhash = PasswordHasher.generateSaltedHash("arbnori123", salt);
//        Perdoruesi perdoruesi = new Perdoruesi(230756101, "Arbnor", "Tasholli",
//                "noltasholli@gmail.com", "044123123", "Admin", salt,
//                saltedhash, new Timestamp(System.currentTimeMillis()));
//        PerdoruesiService perdoruesiService = new PerdoruesiService();
//        perdoruesiService.create(perdoruesi);
//    }
}