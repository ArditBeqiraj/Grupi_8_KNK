package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sherbimet {
    private int sherbimi_id;
    private String emri_sherbimit;
    private String pershkrimi;
    private double cmimi;

    private Sherbimet(int sherbimi_id, String emri_sherbimit, String pershkrimi, double cmimi) {
        this.sherbimi_id = sherbimi_id;
        this.emri_sherbimit = emri_sherbimit;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
    }

    public static Sherbimet getInstance(ResultSet resultSet) throws SQLException {
        int sherbimi_id = resultSet.getInt("sherbimi_id");
        String emri_sherbimit = resultSet.getString("emri_sherbimit");
        String pershkrimi = resultSet.getString("pershkrimi");
        double cmimi = resultSet.getDouble("cmimi");

        return new Sherbimet(sherbimi_id, emri_sherbimit, pershkrimi, cmimi);
    }

    public int getSherbimi_id() {
        return sherbimi_id;
    }

    public String getEmri_sherbimit() {
        return emri_sherbimit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public double getCmimi() {
        return cmimi;
    }
}
