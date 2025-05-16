package services;

import Repository.PerdoruesiRepository;
import app.SessionManager;
import javafx.collections.ObservableList;
import model.Pacientet.Pacientet;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;
import model.Perdoruesi.PerdoruesiDTO.PerdoruesiChangePasswordDto;

import java.sql.SQLException;
import java.sql.Timestamp;

public class PerdoruesiService {
    private PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
    public Perdoruesi create(Perdoruesi perdoruesi) {
        String salt = PasswordHasher.generateSalt();
        String password = perdoruesi.getPassword();

        CreatePerdoruesiDTO createStudentData = new CreatePerdoruesiDTO(
                perdoruesi.getEmri(),
                perdoruesi.getMbiemri(),
                perdoruesi.getEmail(),
                perdoruesi.getTelefoni(),
                perdoruesi.getRoli(),
                salt,
                PasswordHasher.generateSaltedHash(password, salt),
                new Timestamp(System.currentTimeMillis())
        );
        Perdoruesi perdoruesi1 = new PerdoruesiRepository().create(createStudentData);
        return perdoruesi1;
    }

    public static boolean login(LoginPerdoruesiDto loginData) {
        Perdoruesi perdoruesi = new PerdoruesiRepository().getByEmail(loginData.getEmail());

        if(perdoruesi == null){
            return false;
        }

        String password = loginData.getPassword();
        String salt = perdoruesi.getSalt();
        String hashedPassword = PasswordHasher.generateSaltedHash(password, salt);

        if (!PasswordHasher.compareSaltedHash(password, salt, hashedPassword)) {
            return false;
        }

        PerdoruesiSession.setPerdoruesiSession(perdoruesi);
        return true;
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
        SessionManager.setPerdoruesi(null);
    }
}
