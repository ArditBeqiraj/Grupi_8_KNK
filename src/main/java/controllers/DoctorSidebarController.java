package controllers;

import app.Navigator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import services.LanguageManager;

import java.io.IOException;
import java.util.Locale;

public class DoctorSidebarController {

    @FXML
    private FontAwesomeIconView language;

    @FXML
    private Button DoctorHomePage;

    @FXML
    private Button DoctorMyProfilePage;

    @FXML
    private Button DoctorPatientsPage;

    @FXML
    private Button DoctorAppointmentsPage;

    @FXML
    public void initialize() {
        DoctorHomePage.setOnAction(this::handleNavigation);
        DoctorMyProfilePage.setOnAction(this::handleNavigation);
        DoctorPatientsPage.setOnAction(this::handleNavigation);
        DoctorAppointmentsPage.setOnAction(this::handleNavigation);
    }

    private void handleNavigation(ActionEvent event) {
        try {
            if (event.getSource() == DoctorHomePage) {
                Navigator.navigate(event, Navigator.DOCTOR_HOME, "Doctor Home");
            } else if (event.getSource() == DoctorMyProfilePage) {
                Navigator.navigate(event, Navigator.DOCTOR_PROFILE, "My Profile");
            } else if (event.getSource() == DoctorPatientsPage) {
                Navigator.navigate(event, Navigator.DOCTOR_PACIENTS_PAGE, "Patients");
            } else if (event.getSource() == DoctorAppointmentsPage) {
                Navigator.navigate(event, "/app/DoctorAppointments.fxml", "Appointments");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLanguageToggle() throws Exception {
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
