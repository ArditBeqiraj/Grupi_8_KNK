package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.LoginSignupService;
import services.PerdoruesiService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private CheckBox boxSave;

    @FXML
    private Button btnForgot;

    @FXML
    private Button btnSignup;

    @FXML
    private Button btnlogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private FontAwesomeIconView showPassword;

    @FXML
    private TextField passwordtxt;

    private final PerdoruesiService userService = new PerdoruesiService();
    private final LoginSignupService logInSignInService = new LoginSignupService();
    private Stage stage;
    private static String password;
    private static String email;
    private boolean show = true;


    @FXML
    void changeVisibility() {
        if (show) {
            changeVisibilityshow();
        } else if (!show){
            changeVisibilityhide();
        }
    }
    @FXML
    void changeVisibilityshow() {
        passwordtxt.setText(txtPassword.getText());
        passwordtxt.setVisible(true);
        txtPassword.setVisible(false);
        this.show = false;
    }

    @FXML
    void changeVisibilityhide() {
        txtPassword.setText(passwordtxt.getText());
        txtPassword.setVisible(true);
        passwordtxt.setVisible(false);
        this.show = true;
    }

    @FXML
    private void handleLoginClick(ActionEvent ae) throws SQLException, IOException {
        email = txtEmail.getText().trim();
        password = txtPassword.getText().trim();
        logInSignInService.handleLoginClick(ae, email, password);
    }

    @FXML
    public void handleCreateAccountClick(ActionEvent me) throws IOException {
        LoginSignupService.handleCreateAccount(me);
    }

    @FXML
    public void initKeyActions(Scene scene, Stage stage) {
        logInSignInService.initKeyActions(scene, stage, btnlogin, email, password);
    }
}
