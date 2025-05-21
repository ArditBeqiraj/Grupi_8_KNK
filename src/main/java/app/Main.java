package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Perdoruesi.Perdoruesi;
import services.PasswordHasher;
import services.PerdoruesiService;

import java.io.IOException;
import java.sql.Timestamp;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.navigate(stage, Navigator.LOGIN_PAGE, "Admin Dashboard");
    }

//    public static void main(String[] args) {
//       String salt = PasswordHasher.generateSalt();
//       String saltedhash = PasswordHasher.generateSaltedHash("admin123", salt);
//       Perdoruesi perdoruesi = new Perdoruesi(230756101, "Admin", "Admin",
//               "admin@gmail.com", "044123123", "Admin", salt,
//               saltedhash, new Timestamp(System.currentTimeMillis()));
//       PerdoruesiService perdoruesiService = new PerdoruesiService();
//       perdoruesiService.create(perdoruesi);
//
//        System.out.println(PasswordHasher.generateSaltedHash("genti123", "8nKA4bGlUCsgmM+BIxcr6eynb4N6bNuIFko73GVqw8E="));
//    }
}