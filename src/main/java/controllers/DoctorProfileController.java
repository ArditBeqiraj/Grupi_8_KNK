package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.SceneManager;

import java.io.IOException;
import java.sql.SQLException;

public class DoctorProfileController {

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
    private void handleEditButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/ChangePassword.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();


    }
}
