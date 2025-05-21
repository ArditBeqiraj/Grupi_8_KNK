package services;

import Repository.MjeketRepository;
import Repository.PerdoruesiRepository;
import app.SessionManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Mjeket.Mjeket;
import model.Mjeket.MjeketDTO.CreateMjeketDTO;
import model.Pacientet.Pacientet;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;
import model.Perdoruesi.PerdoruesiDTO.PerdoruesiChangePasswordDto;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PerdoruesiService {
    private PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
    public Perdoruesi create(Perdoruesi perdoruesi) {

        CreatePerdoruesiDTO createStudentData = new CreatePerdoruesiDTO(
                perdoruesi.getEmri(),
                perdoruesi.getMbiemri(),
                perdoruesi.getEmail(),
                perdoruesi.getTelefoni(),
                perdoruesi.getRoli(),
                perdoruesi.getSalt(),
                perdoruesi.getHashedpassword(),
                new Timestamp(System.currentTimeMillis())
        );
        Perdoruesi perdoruesi1 = new PerdoruesiRepository().create(createStudentData);
        if (perdoruesi.getRoli().equalsIgnoreCase("Doktor")) {
            CreateMjeketDTO createMjeketDTO = new CreateMjeketDTO(perdoruesi.getEmri(), perdoruesi.getMbiemri(), perdoruesi.getTelefoni(), perdoruesi.getEmail());
            MjeketRepository mjeketRepository = new MjeketRepository();
            Mjeket mjeket = mjeketRepository.create(createMjeketDTO);
        }
        return perdoruesi1;
    }

    public static boolean login(LoginPerdoruesiDto loginData) throws SQLException {
        PerdoruesiRepository perdoruesiRepository1 = new PerdoruesiRepository();
        Perdoruesi perdoruesi = perdoruesiRepository1.getByEmail(loginData.getEmail());
        if (perdoruesi == null) {
            return false;
        }

        String password = loginData.getPassword();
        String salt = perdoruesi.getSalt();
        String passwordHash = perdoruesi.getHashedpassword();


        boolean result = PasswordHasher.compareSaltedHash(password, salt, passwordHash);

        if (result) {
            SessionManager.setPerdoruesi(perdoruesi);
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong email or password!");
            alert.showAndWait();
            return false;
        }
    }






    public boolean updatePassword(PerdoruesiChangePasswordDto saveData) throws SQLException {
        String email = PerdoruesiSession.getPerdoruesi().getEmail();
        Perdoruesi perdoruesi = new PerdoruesiRepository().getByEmail(email);

        if (perdoruesi == null){
            return false;
        }

        String oldPassword = saveData.getOldPassword();
        String newPassword = saveData.getNewPassword();

        String salt = PasswordHasher.generateSalt();
        String newHashedPassword = PasswordHasher.generateSaltedHash(newPassword, salt);

        if(!PasswordHasher.compareSaltedHash(oldPassword, perdoruesi.getSalt(), perdoruesi.getHashedpassword())){
            return false;
        }


        return new PerdoruesiRepository().updatePassword(perdoruesi.getId(), newHashedPassword, salt);
    }

    public boolean updateStaf(Perdoruesi perdoruesi) {
        boolean emailExists = perdoruesiRepository.emailExistsForOtherUser(perdoruesi.getEmail(), perdoruesi.getId());
        if (emailExists) {
            return false;
        }

        perdoruesiRepository.update(perdoruesi);
        return true;
    }

    public ObservableList<Pacientet> fetchAllPacientet() throws SQLException {
        return perdoruesiRepository.getAllPacientet();
    }


    public void LogOut() {
        SessionManager.logout();
    }
}
