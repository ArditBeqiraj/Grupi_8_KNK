package controllers;

import app.Navigator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import services.LanguageManager;

import java.io.IOException;
import java.util.Locale;

public class DoctorHomeController {
        @FXML
        private FontAwesomeIconView language;

        @FXML
        void handleLanguageToggle(MouseEvent event) throws IOException {
            Locale current = LanguageManager.getInstance().getLocale();
            if ("sq".equals(current.getLanguage())) {
                LanguageManager.getInstance().setLocale(new Locale("en"));
                Navigator.setCurrentLocale(new Locale("en"));
            } else {
                LanguageManager.getInstance().setLocale(new Locale("sq"));
                Navigator.setCurrentLocale(new Locale("sq"));
            }
            Navigator.reload();
        }
}
