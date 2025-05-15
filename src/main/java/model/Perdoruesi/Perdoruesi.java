package model.Perdoruesi;

import services.PasswordHasher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Perdoruesi {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String telefoni;
    private String roli;
    private String salt;
    private String hashedpassword;
    private Timestamp krijuar;

    public Perdoruesi(int id, String emri, String mbiemri, String email, String telefoni, String roli, String salt, String hashedpassword, Timestamp krijuar) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.telefoni = telefoni;
        this.roli = roli;
        this.salt = salt;
        this.hashedpassword = hashedpassword;
        this.krijuar = krijuar;
    }

    public static Perdoruesi getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String email = resultSet.getString("email");
        String telefoni = resultSet.getString("telefoni");
        String roli = resultSet.getString("roli");
        String salt = resultSet.getString("salt");
        String hashedpassword = resultSet.getString("hashedpassword");
        Timestamp krijuar = resultSet.getTimestamp("krijuar");

        return new Perdoruesi(id, emri, mbiemri, email, telefoni, roli, salt, hashedpassword, krijuar);
    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public String getRoli() {
        return roli;
    }

    public String getPassword() {
        return hashedpassword;
    }

    public Timestamp getKrijuar() {
        return krijuar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashedpassword() {
        return hashedpassword;
    }

    public void setHashedpassword(String hashedpassword) {
        this.hashedpassword = hashedpassword;
    }


}