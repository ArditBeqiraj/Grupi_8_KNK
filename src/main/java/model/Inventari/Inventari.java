package model.Inventari;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Inventari {
    private int id;
    private String emri_produktit;
    private int sasia;
    private String furnizuesi;

    private Inventari(int id, String emri_produktit, int sasia, String furnizuesi) {
        this.id = id;
        this.emri_produktit = emri_produktit;
        this.sasia = sasia;
        this.furnizuesi = furnizuesi;
    }

    public static Inventari getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emri_produktit = resultSet.getString("emri_produktit");
        int sasia = resultSet.getInt("sasia");
        String furnizuesi = resultSet.getString("furnizuesi");

        return new Inventari(id, emri_produktit, sasia, furnizuesi);
    }

    public int getProdukt_id() {
        return id;
    }

    public String getEmri_produktit() {
        return emri_produktit;
    }

    public int getSasia() {
        return sasia;
    }

    public String getFurnizuesi() {
        return furnizuesi;
    }
}