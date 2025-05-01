package model.Terminet;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Terminet {
    private int id;
    private int id_pacient;
    private int id_mjek;
    private int id_salle;
    private Date data;
    private String statusi;
    private String shenime;

    private Terminet(int id, int id_pacient, int id_mjek, int id_salle, Date data, String statusi, String shenime) {
        this.id = id;
        this.id_pacient = id_pacient;
        this.id_mjek = id_mjek;
        this.id_salle = id_salle;
        this.data = data;
        this.statusi = statusi;
        this.shenime = shenime;
    }

    public static Terminet getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int id_pacient = resultSet.getInt("id_pacient");
        int id_mjek = resultSet.getInt("id_mjek");
        int id_salle = resultSet.getInt("id_salle");
        Date data = resultSet.getDate("data");
        String statusi = resultSet.getString("statusi");
        String shenime = resultSet.getString("shenime");

        return new Terminet(id, id_pacient, id_mjek, id_salle, data, statusi, shenime);
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
}
