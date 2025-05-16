package controllers;

import app.Navigator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import services.LoginSignupService;
import services.PerdoruesiService;

import java.io.IOException;
import java.sql.SQLException;

public class SignupController {

    @FXML
    private Button btnSIgnup;

    @FXML
    private Button btnSingIn;

    @FXML
    private ToggleGroup gjinia;

    @FXML
    private PasswordField pwdConfirmPaswword;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private RadioButton radioFemer;

    @FXML
    private RadioButton radioMashkull;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtNumri;

    @FXML
    private TextField passwordtxt;

    @FXML
    private TextField confirmpasswordtxt;

    @FXML
    private FontAwesomeIconView showpassword;

    @FXML
    private FontAwesomeIconView showconfirmpassword;

    PerdoruesiService perdoruesiService = new PerdoruesiService();
    LoginSignupService loginSignupService = new LoginSignupService();
    private Stage stage;

    private boolean show = true;
    private boolean showconfirm = true;


    @FXML
    void changeVisibility() {
        if (show) {
            changeVisibilityshow();
        } else if (!show){
            changeVisibilityhide();
        }
    }

    @FXML
    void changeVisibilityConfirm() {
        if (showconfirm) {
            changeVisibilityConfirmshow();
        } else if (!showconfirm){
            changeVisibilityConfirmhide();
        }
    }

    @FXML
    void changeVisibilityshow() {
        passwordtxt.setText(pwdPassword.getText());
        passwordtxt.setVisible(true);
        pwdPassword.setVisible(false);
        this.show = false;
    }

    @FXML
    void changeVisibilityhide() {
        pwdPassword.setText(passwordtxt.getText());
        pwdPassword.setVisible(true);
        passwordtxt.setVisible(false);
        this.show = true;
    }

    @FXML
    void changeVisibilityConfirmshow() {
        confirmpasswordtxt.setText(pwdConfirmPaswword.getText());
        confirmpasswordtxt.setVisible(true);
        pwdConfirmPaswword.setVisible(false);
        this.showconfirm = false;
    }

    @FXML
    void changeVisibilityConfirmhide() {
        pwdConfirmPaswword.setText(confirmpasswordtxt.getText());
        pwdConfirmPaswword.setVisible(true);
        confirmpasswordtxt.setVisible(false);
        this.showconfirm = true;
    }

    @FXML
    private void handleSignUp(ActionEvent ae) throws Exception {
        loginSignupService.handleSignUp(ae,txtEmri, txtMbiemri, txtEmail, txtNumri, gjinia, pwdPassword, pwdConfirmPaswword);
    }

    @FXML
    public void initKeyActions(Scene scene, Stage stage) {
        this.stage = stage;
        scene.setOnKeyPressed(keyAction -> {
            if (keyAction.getCode() == KeyCode.ENTER) {
                try {
                    handleSignUp(new ActionEvent(txtEmri, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void gotoLoginPage(ActionEvent me) throws IOException {
        Navigator.navigate(me, Navigator.LOGIN_PAGE, "Login");
    }
}
