package services;

import Repository.PerdoruesiRepository;
import app.Navigator;
import app.SessionManager;
import javafx.scene.control.Alert;
import model.Perdoruesi.Perdoruesi;

public class ChangePasswordService {
    PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
    Perdoruesi perdoruesi = SessionManager.getPerdoruesi();
    PerdoruesiService perdoruesiService = new PerdoruesiService();
    public boolean onSubmit(String password, String newPassword, String confirmPassword) {
        String salt = perdoruesi.getSalt();
        String hashedPassword = perdoruesi.getHashedpassword();
        if (!hashedPassword.equals(PasswordHasher.generateSaltedHash(password, salt)) || !newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("ka ndodhur nje gabim");
            alert.showAndWait();
        } else {
            String newSalt = PasswordHasher.generateSalt();
            String newHashed = PasswordHasher.generateSaltedHash(newPassword, newSalt);
            perdoruesi.setHashedpassword(PasswordHasher.generateSaltedHash(newPassword, newSalt));
            perdoruesiRepository.updatePassword(perdoruesi.getId(), newHashed, newSalt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password changed successfully!");
            alert.showAndWait();
            return true;
        }
        return false;
    }

}
