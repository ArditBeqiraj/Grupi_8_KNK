package services;

import Repository.PerdoruesiRepository;
import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LoginSignupService {
    private Stage stage;

    public void handleLoginClick(ActionEvent ae, String email, String password) throws SQLException, IOException {
        LoginPerdoruesiDto loginPerdoruesiData = new LoginPerdoruesiDto(email, password);

        if (PerdoruesiService.login(loginPerdoruesiData)) {
            PerdoruesiRepository repo = new PerdoruesiRepository();
            SessionManager.setPerdoruesi(repo.getByEmail(email));
            Perdoruesi user = SessionManager.getPerdoruesi();
            if (user != null) {
                if (user.getRoli().equals("Admin")) {
                    Navigator.navigate(ae, Navigator.ADMIN_DASHBOARD, "Admin Dashboard");
                    System.out.println("Po hapet admini");
                } else if (user.getRoli().equals("Pacient")) {
                    Navigator.navigate(ae, Navigator.DOCTOR_PPROFILE, "Pacienti");
                } else {
                    Navigator.navigate(ae, Navigator.STAFF_HOME, "Stafi");
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

    public void handleSignUp(ActionEvent ae, TextField txtEmri, TextField txtMbeimri, TextField txtEmail, TextField txtnumri, ToggleGroup gjinia, PasswordField pwdPassword, PasswordField pwdConfirmPassword) throws SQLException, IOException {
        String emri = txtEmri.getText();
        String mbiemri = txtMbeimri.getText();
        String email = txtEmail.getText();
        String numri = txtnumri.getText();
        String gender = gjinia.toString();
        String password = pwdPassword.getText();
        String confirmPassword = pwdConfirmPassword.getText();

        if (emri.isEmpty() || mbiemri.isEmpty() || email.isEmpty() || numri.isEmpty() || gender.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(PerdoruesiService.class.getResource("/app/fill.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Fill all fields");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }


        if (!password.equals(confirmPassword)) {
            try {
                FXMLLoader loader = new FXMLLoader(PerdoruesiService.class.getResource("/app/fill.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Password do NOT match");
                stage.initModality(Modality.APPLICATION_MODAL);


                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }


        String salt = PasswordHasher.generateSalt();
        CreatePerdoruesiDTO createPerdoruesiDTO = new CreatePerdoruesiDTO(emri, mbiemri, email, numri, "Pacient", salt, PasswordHasher.generateSaltedHash(salt, password), new Timestamp(System.currentTimeMillis()));

        PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
        Perdoruesi perdoruesi = perdoruesiRepository.create(createPerdoruesiDTO);


        Navigator.navigate(ae, Navigator.LOGIN_PAGE, "Login");
    }

}
