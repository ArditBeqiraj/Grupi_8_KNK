//package controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.stage.Stage;
//import model.Perdoruesi.Perdoruesi;
//import services.PerdoruesiService;
//import services.PasswordHasher;
//
//public class ChangePasswordController {
//
//    @FXML
//    private PasswordField currentPasswordField;
//
//    @FXML
//    private PasswordField newPasswordField;
//
//    @FXML
//    private PasswordField confirmPasswordField;
//
//    @FXML
//    private Button backButton;
//
//    @FXML
//    private Button submitButton;
//
//    private final PerdoruesiService perdoruesiService = new PerdoruesiService();
//
//    private Perdoruesi loggedInUser;
//
//    public void setLoggedInUser(Perdoruesi user) {
//        this.loggedInUser = user;
//    }
//
//    @FXML
//    private void initialize() {
//        submitButton.setOnAction(event -> handleChangePassword());
//        backButton.setOnAction(event -> closeWindow());
//    }
//
//    private void handleChangePassword() {
//        String currentPassword = currentPasswordField.getText();
//        String newPassword = newPasswordField.getText();
//        String confirmPassword = confirmPasswordField.getText();
//
//        if (loggedInUser == null) {
//            showAlert("Error", "No user is logged in.");
//            return;
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            showAlert("Error", "New passwords do not match.");
//            return;
//        }
//
//        boolean currentPasswordCorrect = PasswordHasher.verifyPassword(currentPassword, loggedInUser.getPassword(), loggedInUser.getSalt());
//        if (!currentPasswordCorrect) {
//            showAlert("Error", "Current password is incorrect.");
//            return;
//        }
//
//        try {
//            boolean success = perdoruesiService.updatePassword(loggedInUser.getId(), newPassword);
//            if (success) {
//                showAlert("Success", "Password updated successfully.");
//                closeWindow();
//            } else {
//                showAlert("Error", "Could not update the password.");
//            }
//        } catch (Exception e) {
//            showAlert("Exception", "Something went wrong.");
//            e.printStackTrace();
//        }
//    }
//
//    private void closeWindow() {
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        stage.close();
//    }
//
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}
