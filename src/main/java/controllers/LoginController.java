package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;

import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;
import services.PerdoruesiService;
import services.SceneManager;
import utils.Validator;

public class LoginController {
    @FXML
    private TextField txtStudentId;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text txtErrorMessage;

    @FXML
    private void initialize() {
        EventHandler<KeyEvent> enterKeyHandler = event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handleLoginStudent();
                    if (txtErrorMessage.getText().isEmpty()) {
                        SceneManager.load(SceneManager.ADMINHOME_PAGE);
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        pwdPassword.setOnKeyPressed(enterKeyHandler);
        btnLogin.setOnKeyPressed(enterKeyHandler);
    }

    @FXML
    private void handleLoginStudentClick(MouseEvent event) throws Exception {
        handleLoginStudent();
        if (txtErrorMessage.getText().isEmpty()) {
            SceneManager.load(SceneManager.ADMINHOME_PAGE);
        }
    }

    @FXML
    private void handleLoginStudent() throws SQLException {
        if (Validator.isEmpty(txtStudentId.getText(), pwdPassword.getText())) {
            txtErrorMessage.setText("Please fill in all fields");
            return;
        }

        LoginPerdoruesiDto loginUserDto = new LoginPerdoruesiDto(
                Validator.clearSpaces(this.txtStudentId.getText()),
                Validator.clearSpaces(this.pwdPassword.getText())
        );

        boolean isLogin = PerdoruesiService.login(loginUserDto);

        if(!isLogin) {
            txtErrorMessage.setText("Username or password is incorrect");
            return;
        }

        txtErrorMessage.setText("");
    }
}
