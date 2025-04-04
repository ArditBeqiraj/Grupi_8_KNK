package model.Perdoruesi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Perdoruesi {
    private int user_id;
    private String emri;
    private String mbiemri;
    private String email;
    private String telefoni;
    private String roli;
    private String password;
    private Timestamp krijuar;

    private Perdoruesi(int user_id, String emri, String mbiemri, String email, String telefoni, String roli, String password, Timestamp krijuar) {
        this.user_id = user_id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.telefoni = telefoni;
        this.roli = roli;
        this.password = password;
        this.krijuar = krijuar;
    }

    public static Perdoruesi getInstance(ResultSet resultSet) throws SQLException {
        int user_id = resultSet.getInt("user_id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String email = resultSet.getString("email");
        String telefoni = resultSet.getString("telefoni");
        String roli = resultSet.getString("roli");
        String password = resultSet.getString("password");
        Timestamp krijuar = resultSet.getTimestamp("krijuar");

        return new Perdoruesi(user_id, emri, mbiemri, email, telefoni, roli, password, krijuar);
    }

    public int getUser_id() {
        return user_id;
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
        return password;
    }

    public Timestamp getKrijuar() {
        return krijuar;
    }
}