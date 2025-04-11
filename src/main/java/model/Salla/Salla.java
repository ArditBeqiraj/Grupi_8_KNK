package model.Salla;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Salla {
    private int id;
    private String emri_salles;
    private String tipi;
    private int kapaciteti;
    private String statusi;

    public Salla(int id, String emri_salles, String tipi, int kapaciteti, String statusi) {
        this.id = id;
        this.emri_salles = emri_salles;
        this.tipi = tipi;
        this.kapaciteti = kapaciteti;
        this.statusi = statusi;
    }
    public static Salla getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("salla_id");
        String emri_salles = resultSet.getString("emri_salles");
        String tipi = resultSet.getString("tipi");
        int kapaciteti = resultSet.getInt("kapaciteti");
        String statusi = resultSet.getString("statusi");
        return new Salla(id,emri_salles,tipi,kapaciteti,statusi);

    }

    public int getId() {
        return id;
    }

    public String getEmri_salles() {
        return emri_salles;
    }

    public String getTipi() {
        return tipi;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }

    public String getStatusi() {
        return statusi;
    }
}
