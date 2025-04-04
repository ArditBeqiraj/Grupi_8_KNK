package model.Orari;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Orari {
    private int orari_id;
    private int perdoruesi_id;
    private Timestamp fillimi_ndrrimit;
    private Timestamp perfundimi_ndrrimit;
    private String roli;

    private Orari(int orari_id, int perdoruesi_id, Timestamp fillimi_ndrrimit, Timestamp perfundimi_ndrrimit, String roli) {
        this.orari_id = orari_id;
        this.perdoruesi_id = perdoruesi_id;
        this.fillimi_ndrrimit = fillimi_ndrrimit;
        this.perfundimi_ndrrimit = perfundimi_ndrrimit;
        this.roli = roli;
    }

    public static Orari getInstance(ResultSet resultSet) throws SQLException {
        int orari_id = resultSet.getInt("orari_id");
        int perdoruesi_id = resultSet.getInt("perdoruesi_id");
        Timestamp fillimi_ndrrimit = resultSet.getTimestamp("fillimi_ndrrimit");
        Timestamp perfundimi_ndrrimit = resultSet.getTimestamp("perfundimi_ndrrimit");
        String roli = resultSet.getString("roli");

        return new Orari(orari_id, perdoruesi_id, fillimi_ndrrimit, perfundimi_ndrrimit, roli);
    }

    public int getOrari_id() {
        return orari_id;
    }

    public int getPerdoruesi_id() {
        return perdoruesi_id;
    }

    public Timestamp getFillimi_ndrrimit() {
        return fillimi_ndrrimit;
    }

    public Timestamp getPerfundimi_ndrrimit() {
        return perfundimi_ndrrimit;
    }

    public String getRoli() {
        return roli;
    }
}