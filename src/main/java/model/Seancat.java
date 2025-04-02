package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Seancat {
    private int seanca_id;
    private int pacient_id;
    private int doktor_id;
    private Timestamp data_seances;
    private String statusi;
    private String shenime;

    private Seancat(int seanca_id, int pacient_id, int doktor_id, Timestamp data_seances, String statusi, String shenime) {
        this.seanca_id = seanca_id;
        this.pacient_id = pacient_id;
        this.doktor_id = doktor_id;
        this.data_seances = data_seances;
        this.statusi = statusi;
        this.shenime = shenime;
    }

    public static Seancat getInstance(ResultSet resultSet) throws SQLException {
        int seanca_id = resultSet.getInt("seanca_id");
        int pacient_id = resultSet.getInt("pacient_id");
        int doktor_id = resultSet.getInt("doktor_id");
        Timestamp data_seances = resultSet.getTimestamp("data_seances");
        String statusi = resultSet.getString("statusi");
        String shenime = resultSet.getString("shenime");

        return new Seancat(seanca_id, pacient_id, doktor_id, data_seances, statusi, shenime);
    }

    public int getSeanca_id() {
        return seanca_id;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public int getDoktor_id() {
        return doktor_id;
    }

    public Timestamp getData_seances() {
        return data_seances;
    }

    public String getStatusi() {
        return statusi;
    }

    public String getShenime() {
        return shenime;
    }
}
