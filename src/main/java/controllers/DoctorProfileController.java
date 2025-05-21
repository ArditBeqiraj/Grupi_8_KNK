package controllers;

import app.Navigator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.LanguageManager;
import services.SceneManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class DoctorProfileController {

    @FXML
    private FontAwesomeIconView language;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField licenseNumberField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField specializationsField;

    @FXML
    private Button editButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button changePasswordButton;

    private static boolean checkEdit = false;

    @FXML
    private void handleEditAction() {
       if(checkEdit){
           checkEdit=false;
        setFieldsEditable(checkEdit);
       }else{
           checkEdit=true;
           setFieldsEditable(checkEdit);
       }
    }

    @FXML
    private void handleSubmitAction() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String licenseNumber = licenseNumberField.getText();
        String phone = phoneNumberField.getText();
        String email = emailField.getText();
        String specializations = specializationsField.getText();

        System.out.println("Doctor Info Updated:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("License No: " + licenseNumber);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Specializations: " + specializations);

        setFieldsEditable(false);
    }

    private void setFieldsEditable(boolean editable) {
        nameField.setEditable(editable);
        surnameField.setEditable(editable);
        licenseNumberField.setEditable(editable);
        phoneNumberField.setEditable(editable);
        emailField.setEditable(editable);
        specializationsField.setEditable(editable);
    }

    @FXML
    public void initialize() {
        setFieldsEditable(false);
    }

    @FXML
    private void handleEditButtonClick(Event event) throws IOException {
        Navigator.navigate(event, Navigator.CHANGE_PASSWORD, "Change Password");
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
