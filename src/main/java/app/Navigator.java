package app;

import controllers.LoginController;
import controllers.SignupController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "Login_page.fxml";
    public final static String SIGNUP_PAGE = "Signup_page.fxml";
    public final static String DOCTOR_PROFILE = "/app/DoctorProfile.fxml";
    public final static String DOCTOR_HOME = "/app/DoctorHome.fxml";
    public final static String DOCTOR_PACIENTS_PAGE = "/app/DoctorPatientsPage.fxml";
    public final static String ADMIN_DASHBOARD="AdminHome_page.fxml";
    public final static String STAFF_HOME = "/app/StaffHome.fxml";

    public static void navigate(Stage stage, String page, String title) throws IOException {
        stage.centerOnScreen();

        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page));

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
}

