package services;

import Repository.PerdoruesiRepository;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.LoginPerdoruesiDto;
import model.Perdoruesi.PerdoruesiDTO.PerdoruesiChangePasswordDto;

import java.sql.SQLException;
import java.sql.Timestamp;

public class PerdoruesiService {
    public Perdoruesi create(Perdoruesi perdoruesi) {
        String salt = PasswordHasher.generateSalt();
        String password = PasswordHasher.generateSaltedHash("12345", salt);

        CreatePerdoruesiDTO createStudentData = new CreatePerdoruesiDTO(
                perdoruesi.getEmri(),
                perdoruesi.getMbiemri(),
                perdoruesi.getEmail(),
                perdoruesi.getTelefoni(),
                perdoruesi.getRoli(),
                salt,
                password,
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
        String hashedPassword = perdoruesi.getHashedpassword();

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
}
