package controllers;

//coming soon...

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import services.LoginSignupService;
import app.Navigator;

import java.io.IOException;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button backButton;

    private final LoginSignupService loginSignupService = new LoginSignupService();

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        try {
            loginSignupService.handleChangePassword(currentPassword, newPassword, confirmPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Navigator.navigate(event, Navigator.LOGIN_PAGE, "Login");
    }
}
