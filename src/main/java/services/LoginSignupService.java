package services;

import Repository.MjeketRepository;
import Repository.PacientetRepository;
import Repository.PerdoruesiRepository;
import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pacientet.PacientetDTO.CreatePacientetDTO;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class LoginSignupService {
    private Stage stage;

    public void handleLoginClick(ActionEvent ae, String email, String password) throws SQLException, IOException {
        LoginPerdoruesiDto loginPerdoruesiData = new LoginPerdoruesiDto(email, password);
        if (PerdoruesiService.login(loginPerdoruesiData)) {
            PerdoruesiRepository repo = new PerdoruesiRepository();
            MjeketRepository mjeketRepository = new MjeketRepository();
            SessionManager.setPerdoruesi(repo.getByEmail(email));
            Perdoruesi user = SessionManager.getPerdoruesi();
            if (user != null) {
                if (user.getRoli().equals("Admin")) {
                    Navigator.navigate(ae, Navigator.ADMIN_DASHBOARD, "Admin Dashboard");
                } else if (user.getRoli().equals("Doktor")) {
                    SessionManager.setMjeku(mjeketRepository.getByEmail(email));
                    Navigator.navigate(ae, Navigator.DOCTOR_HOME, "Doktor");
                } else if (user.getRoli().equals("Pacient")){
                    Navigator.navigate(ae, Navigator.PATIENT_HOME, "Pacient");
                }
            }
        }
    }

    public static void handleCancel(TextField txtEmail, PasswordField pwdPassword) {
        txtEmail.clear();
        pwdPassword.clear();
    }

    public static void handleCreateAccount(ActionEvent me) throws IOException {
        Navigator.navigate(me, Navigator.SIGNUP_PAGE, "Sign Up");
    }

    public void initKeyActions(Scene scene, Stage stage, Button btnlogin, String email, String password) {
        this.stage = stage;
        scene.setOnKeyPressed(keyAction -> {
            if (keyAction.getCode() == KeyCode.ENTER) {
                try {
                    handleLoginClick(new ActionEvent(btnlogin, null), email, password);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void handleCancel(TextField txtEmri, TextField txtMbeimri, PasswordField pwdPassword, PasswordField pwdConfirmPassword){
        txtEmri.clear();
        txtMbeimri.clear();
        pwdPassword.clear();
        pwdConfirmPassword.clear();
    }

    public void handleSignUp(ActionEvent ae,
                             TextField txtEmri,
                             TextField txtMbiemri,
                             TextField txtEmail,
                             TextField txtNumri,
                             ToggleGroup gjinia,
                             PasswordField pwdPassword,
                             PasswordField pwdConfirmPassword) throws Exception {

        String emri = txtEmri.getText().trim();
        String mbiemri = txtMbiemri.getText().trim();
        String email = txtEmail.getText().trim();
        String numri = txtNumri.getText().trim();
        String password = pwdPassword.getText();
        String confirmPassword = pwdConfirmPassword.getText();

        Toggle selectedToggle = gjinia.getSelectedToggle();
        if (selectedToggle == null) {
            showError("Please select a gender.");
            return;
        }
        String gender = ((RadioButton) selectedToggle).getText();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || numri.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        String salt = PasswordHasher.generateSalt();
        String hashedPassword = PasswordHasher.generateSaltedHash(password, salt);

        CreatePerdoruesiDTO createPerdoruesiDTO = new CreatePerdoruesiDTO(
                emri, mbiemri, email, numri, "Pacient", salt, hashedPassword,
                new Timestamp(System.currentTimeMillis())
        );

        PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
        Perdoruesi createdUser = perdoruesiRepository.create(createPerdoruesiDTO);

        if (createdUser == null) {
            showError("User creation failed!");
            return;
        } else {
            System.out.println("Perdoruesi u krijua!");

            // Shto pacientin në tabelën `pacientet`
            PacientetRepository pacientetRepo = new PacientetRepository();
            CreatePacientetDTO pacientDTO = new CreatePacientetDTO(
                    createdUser.getId(),
                    null,
                    gender,
                    null,
                    numri,
                    null
            );

            pacientetRepo.create(pacientDTO);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Account registered successfully!");
            alert.showAndWait();
        }

        Navigator.navigate(ae, Navigator.LOGIN_PAGE, "Login");
    }



    private void showError(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fill.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(message);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleChangePassword(String currentPassword, String newPassword, String confirmPassword) {
        Perdoruesi currentUser = SessionManager.getPerdoruesi();
        if (currentUser == null) {
            System.out.println("No user in session.");
            return;
        }

        boolean isCorrectPassword = PasswordHasher.compareSaltedHash(
                currentPassword,
                currentUser.getSalt(),
                currentUser.getHashedpassword()
        );

        if (!isCorrectPassword) {
            showError("Current password is incorrect.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showError("New passwords do not match.");
            return;
        }

        String newSalt = PasswordHasher.generateSalt();
        String newSaltedHash = PasswordHasher.generateSaltedHash(newPassword, newSalt);

        try {
            PerdoruesiRepository repo = new PerdoruesiRepository();
            repo.updatePassword(currentUser.getId(), newSalt, newSaltedHash);
            System.out.println("Password changed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Database error while changing password.");
        }
    }

}
