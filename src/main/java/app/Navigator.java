package app;

import controllers.LoginController;
import controllers.SignupController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Navigator {
    public final static String LOGIN_PAGE = "Login_page.fxml";
    public final static String SIGNUP_PAGE = "Signup_page.fxml";
    public final static String DOCTOR_PROFILE = "/app/DoctorProfile.fxml";
    public final static String DOCTOR_HOME = "/app/DoctorHome.fxml";
    public final static String DOCTOR_PACIENTS_PAGE = "/app/DoctorPatientsPage.fxml";
    public final static String ADMIN_DASHBOARD="AdminHome_page.fxml";
    public final static String STAFF_HOME = "/app/StaffHome.fxml";
    public final static String PATIENT_HOME = "PatientHome.fxml";
    public final static String CHANGE_PASSWORD = "/app/ChangePassword.fxml";

    private static String lastLoadedPage = null;
    private static Stage currentStage = null;
    private static Locale currentLocale = new Locale("sq");

    public static void navigate(Stage stage, String page, String title) throws IOException {
        stage.centerOnScreen();

        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", currentLocale);

        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page), bundle);
        Scene scene = new Scene(loader.load());


        Object controller = loader.getController();
        if (controller instanceof LoginController) {
            ((LoginController) controller).initKeyActions(scene, stage);
        }
        else if (controller instanceof SignupController) {
            ((SignupController) controller).initKeyActions(scene, stage);
        }

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();

        lastLoadedPage = page;
        currentStage = stage;
    }

    public static void navigate(Event event, String page, String title) throws IOException {
        Stage stage;
        if (event != null) {
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
        } else {
            stage = new Stage();
        }
        navigate(stage, page, title);
    }

    public static void reload() throws IOException {
        if (currentStage != null && lastLoadedPage != null) {
            navigate(currentStage, lastLoadedPage, currentStage.getTitle());
            System.out.println(currentLocale);
        } else {
            throw new IllegalStateException("No page loaded yet, cannot reload.");
        }
    }

    public static void setCurrentLocale(Locale currentLocale) {
        Navigator.currentLocale = currentLocale;
    }
}

