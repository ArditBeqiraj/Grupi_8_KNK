package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    // Ky metodë aktivizon fushat për redaktim
    @FXML
    private void handleEditAction() {
        setFieldsEditable(true);
    }

    // Ky metodë ruan të dhënat (mund të lidhet me databazë më vonë)
    @FXML
    private void handleSubmitAction() {
        // Shembull i thjeshtë: marrja e të dhënave
        String name = nameField.getText();
        String surname = surnameField.getText();
        String licenseNumber = licenseNumberField.getText();
        String phone = phoneNumberField.getText();
        String email = emailField.getText();
        String specializations = specializationsField.getText();

        // Mund të shtosh këtu validime ose ruajtje në databazë
        System.out.println("Doctor Info Updated:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("License No: " + licenseNumber);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Specializations: " + specializations);

        setFieldsEditable(false); // Pas submit-it, i bëjmë të pa-editueshme
    }

    // Funksion ndihmës për të aktivizuar ose çaktivizuar fushat
    private void setFieldsEditable(boolean editable) {
        nameField.setEditable(editable);
        surnameField.setEditable(editable);
        licenseNumberField.setEditable(editable);
        phoneNumberField.setEditable(editable);
        emailField.setEditable(editable);
        specializationsField.setEditable(editable);
    }

    // Mund të shtosh një metodë initialize nëse do të ngarkosh të dhëna ekzistuese
    @FXML
    public void initialize() {
        setFieldsEditable(false); // Fushat nuk janë të editueshme në fillim
    }
}
