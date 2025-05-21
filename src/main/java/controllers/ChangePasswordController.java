package controllers;

import app.Navigator;
import app.SessionManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Perdoruesi.Perdoruesi;
import services.ChangePasswordService;
import services.PerdoruesiService;

import java.io.IOException;

public class ChangePasswordController {

    @FXML
    private Button backButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField currentpasswordtxt;

    @FXML
    private TextField newpasswordtxt;

    @FXML
    private TextField confirmpasswordtxt;

    private boolean showCurrent = false;
    private boolean showNew = false;
    private boolean showConfirm = false;

    @FXML
    void toggleCurrentPasswordVisibility() {
        showCurrent = toggleVisibility(showCurrent, currentPasswordField, currentpasswordtxt);
    }

    @FXML
    void toggleNewPasswordVisibility() {
        showNew = toggleVisibility(showNew, newPasswordField, newpasswordtxt);
    }

    @FXML
    void toggleConfirmPasswordVisibility() {
        showConfirm = toggleVisibility(showConfirm, confirmPasswordField, confirmpasswordtxt);
    }

    private boolean toggleVisibility(boolean isShown, PasswordField pwdField, TextField txtField) {
        if (isShown) {
            pwdField.setText(txtField.getText());
            pwdField.setVisible(true);
            txtField.setVisible(false);
        } else {
            txtField.setText(pwdField.getText());
            txtField.setVisible(true);
            pwdField.setVisible(false);
        }
        return !isShown;
    }



    Perdoruesi perdoruesi = SessionManager.getPerdoruesi();
    ChangePasswordService changePasswordService = new ChangePasswordService();

    @FXML
    private void onSubmit(Event event) throws IOException {
        String password = currentPasswordField.isVisible()
                ? currentPasswordField.getText()
                : currentpasswordtxt.getText();

        String newpassword = newPasswordField.isVisible()
                ? newPasswordField.getText()
                : newpasswordtxt.getText();

        String confirmpassword = confirmPasswordField.isVisible()
                ? confirmPasswordField.getText()
                : confirmpasswordtxt.getText();

        if(changePasswordService.onSubmit(password, newpassword, confirmpassword)) {
            if (perdoruesi.getRoli().equals("Pacient")) {
                Navigator.navigate(event, Navigator.PATIENT_HOME, "Patient");
            } else if (perdoruesi.getRoli().equals("Doktor")) {
                Navigator.navigate(event, Navigator.DOCTOR_PROFILE, "Change Password");
            }
        }
    }

    @FXML
    private void onBack(Event event) throws IOException {
        if (perdoruesi.getRoli().equals("Pacient")) {
            Navigator.navigate(event, Navigator.PATIENT_HOME, "Patient");
        } else if (perdoruesi.getRoli().equals("Doktor")) {
            Navigator.navigate(event, Navigator.DOCTOR_PROFILE, "Change Password");
        }
    }

}
