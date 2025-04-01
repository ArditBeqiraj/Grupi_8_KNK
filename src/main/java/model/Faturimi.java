package model;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Faturimi {
    private int fatura_id;
    private int pacient_id;
    private int sherbimi_id;
    private double shuma;
    private String statusi;
    private java.sql.Date krijuar;

    private Faturimi(int fatura_id, int pacient_id, int sherbimi_id, double shuma, String statusi, java.sql.Date krijuar) {
        this.fatura_id = fatura_id;
        this.pacient_id = pacient_id;
        this.sherbimi_id = sherbimi_id;
        this.shuma = shuma;
        this.statusi = statusi;
        this.krijuar = krijuar;
    }

    public static Faturimi getInstance(ResultSet resultSet) throws SQLException {
        int fatura_id = resultSet.getInt("fatura_id");
        int pacient_id = resultSet.getInt("pacient_id");
        int sherbimi_id = resultSet.getInt("sherbimi_id");
        double shuma = resultSet.getDouble("shuma");
        String statusi = resultSet.getString("statusi");
        java.sql.Date krijuar = resultSet.getDate("krijuar");

        return new Faturimi(fatura_id, pacient_id, sherbimi_id, shuma, statusi, krijuar);
    }

    public int getFatura_id() {
        return fatura_id;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public int getSherbimi_id() {
        return sherbimi_id;
    }

    public double getShuma() {
        return shuma;
    }

    public String getStatusi() {
        return statusi;
    }

    public java.sql.Date getKrijuar() {
        return krijuar;
    }
}