package model.Mjeket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mjeket {
    private int id;
    private String emri;
    private String mbiemri;
    private String nr_licences;
    private String nr_telefoni;
    private String email;
    private String specializimi_kryesor;

    private Mjeket(int id, String emri, String mbiemri, String nr_licences, String nr_telefoni, String email, String specializimi_kryesor) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.nr_licences = nr_licences;
        this.nr_telefoni = nr_telefoni;
        this.email = email;
        this.specializimi_kryesor = specializimi_kryesor;
    }
    public static Mjeket getInstance(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String nr_licences = resultSet.getString("nr_licences");
        String nr_telefoni = resultSet.getString("nr_telefoni");
        String email = resultSet.getString("email");
        String specializimi_kryesor = resultSet.getString("specializimi_kryesor");
        return new Mjeket(id,emri,mbiemri,nr_licences,nr_telefoni,email,specializimi_kryesor);

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

    public String getNr_licences() {
        return nr_licences;
    }

    public String getNr_telefoni() {
        return nr_telefoni;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecializimi_kryesor() {
        return specializimi_kryesor;
    }

}
