package controllers;

import Repository.MjeketRepository;
import app.Navigator;
import app.SessionManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Mjeket.Mjeket;
import model.Perdoruesi.Perdoruesi;
import services.LanguageManager;
import services.SceneManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.regex.Pattern;

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

    MjeketRepository mjeketRepository = new MjeketRepository();
    private static boolean checkEdit = false;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

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
        Mjeket mjeku = mjeketRepository.getById(SessionManager.getMjeku().getId());
        nameField.setText(mjeku.getEmri());
        surnameField.setText(mjeku.getMbiemri());
        licenseNumberField.setText(mjeku.getNr_licences());
        specializationsField.setText(mjeku.getNr_licences());
        phoneNumberField.setText(mjeku.getNr_telefoni());
        emailField.setText(mjeku.getEmail());
    }

    @FXML
    private void handlechangePasswordClick(Event event) throws IOException {
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

    public void update() throws SQLException {
        if (nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty() ||
                 licenseNumberField.getText().isEmpty() ||
                specializationsField.getText().isEmpty()) {

            showErrorAlert("Të gjitha fushat duhet të mbushen.");
            return;
        }

        if (!isValidEmail(emailField.getText())) {
            showErrorAlert("Ju lutemi futni një email të vlefshëm.");
            return;
        }

        Mjeket mjeket = new Mjeket();
        mjeket.setId(SessionManager.getMjeku().getId());
        mjeket.setEmri(nameField.getText());
        mjeket.setMbiemri(surnameField.getText());
        mjeket.setEmail(emailField.getText());
        mjeket.setNr_telefoni(phoneNumberField.getText());
        mjeket.setNr_licences(licenseNumberField.getText());
        mjeket.setSpecializimi_kryesor(specializationsField.getText());
        SessionManager.setMjeku(mjeket);

        boolean updated = mjeketRepository.update(mjeket);

        if (updated) {
            showInfoAlert("Përdoruesi u përditësua me sukses.");
            nameField.setText(mjeket.getEmri());
            surnameField.setText(mjeket.getMbiemri());
            licenseNumberField.setText(mjeket.getNr_licences());
            specializationsField.setText(mjeket.getNr_licences());
            phoneNumberField.setText(mjeket.getNr_telefoni());
            emailField.setText(mjeket.getEmail());
        } else {
            showErrorAlert("Përdoruesi nuk ekziston.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
