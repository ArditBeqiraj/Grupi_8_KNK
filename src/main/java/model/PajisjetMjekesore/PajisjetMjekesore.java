package model.PajisjetMjekesore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PajisjetMjekesore {
    private int id;
    private String emri_pajisjes;
    private String pershkrimi;
    private int sasia;
    private String gjendja;
    private Integer salla_id;

    public PajisjetMjekesore(int id, String emri_pajisjes, String pershkrimi, int sasia, String gjendja, Integer salla_id) {
        this.id = id;
        this.emri_pajisjes = emri_pajisjes;
        this.pershkrimi = pershkrimi;
        this.sasia = sasia;
        this.gjendja = gjendja;
        this.salla_id = salla_id;
    }
    public static PajisjetMjekesore getInstance(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("pajisja_id");
        String emri_pajisjes = resultSet.getString("emri_pajisjes");
        String pershkrimi = resultSet.getString("pershkrimi");
        int sasia = resultSet.getInt("sasia");
        String gjendja = resultSet.getString("gjendja");
        Integer salla_id = resultSet.getInt("salla_id");
        if (resultSet.wasNull()) {
            salla_id = null;
        }
        return new PajisjetMjekesore(id,emri_pajisjes,pershkrimi,sasia,gjendja,salla_id);

    };

    public int getId() {
        return id;
    }

    public String getEmri_pajisjes() {
        return emri_pajisjes;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public int getSasia() {
        return sasia;
    }

    public String getGjendja() {
        return gjendja;
    }

    public Integer getSalla_id() {
        return salla_id;
    }
}
