package model.Rekordet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Rekordet {
    Date date = new Date();
    private int rekord_id;
    private int pacient_id;
    private int doktor_id;
    private String diagnoza;
    private String udhezimi;
    private java.sql.Date data_vizites = new java.sql.Date(date.getTime());

    private Rekordet(int rekord_id, int pacient_id, int doktor_id, String diagnoza, String udhezimi, java.sql.Date data_vizites) {
        this.rekord_id = rekord_id;
        this.pacient_id = pacient_id;
        this.doktor_id = doktor_id;
        this.diagnoza = diagnoza;
        this.udhezimi = udhezimi;
        this.data_vizites = data_vizites;
    }

    public static Rekordet getInstance(ResultSet resultSet) throws SQLException {
        int rekord_id = resultSet.getInt("rekord_id");
        int pacient_id = resultSet.getInt("pacient_id");
        int doktor_id = resultSet.getInt("doktor_id");
        String diagnoza = resultSet.getString("diagnoza");
        String udhezimi = resultSet.getString("udhezimi");
        java.sql.Date data_vizites = resultSet.getDate("data_vizites");

        return new Rekordet(rekord_id, pacient_id, doktor_id, diagnoza, udhezimi, data_vizites);
    }

    public int getRekord_id() {
        return rekord_id;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public int getDoktor_id() {
        return doktor_id;
    }

    public String getDiagnoza() {
        return diagnoza;
    }

    public String getUdhezimi() {
        return udhezimi;
    }

    public java.sql.Date getData_vizites() {
        return data_vizites;
    }
}
