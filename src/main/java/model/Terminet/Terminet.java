package model.Terminet;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Terminet {
    private int id;
    private int id_pacient;
    private int id_mjek;
    private String mjeku;
    private int id_salle;
    private Date data;
    private String statusi;
    private String shenime;

    public Terminet(String mjeku,Date data, String statusi, String shenime) {
        this.mjeku = mjeku;
        this.data = data;
        this.statusi = statusi;
        this.shenime = shenime;
    }

    public static Terminet getInstance(ResultSet resultSet) throws SQLException {
        String mjeku = resultSet.getString("id_mjek");
        Date data = resultSet.getDate("data");
        String statusi = resultSet.getString("statusi");
        String shenime = resultSet.getString("shenime");

        return new Terminet(mjeku,data, statusi, shenime);
    }

    public int getId() {
        return id;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public int getId_mjek() {
        return id_mjek;
    }

    public int getId_salle() {
        return id_salle;
    }

    public Date getData() {
        return data;
    }

    public String getStatusi() {
        return statusi;
    }

    public String getShenime() {
        return shenime;
    }
    public String getMjeku() {
        return mjeku;
    }
}
