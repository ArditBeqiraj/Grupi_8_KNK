package model.Specializimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Specializimet {
    private int id;
    private String emri_specializimit;
    private Specializimet(int id,String emri_specializimit){
        this.id=id;
        this.emri_specializimit=emri_specializimit;
    }
    public static Specializimet getInstance(ResultSet resultSet) throws SQLException{
        int id=resultSet.getInt("id");
        String emri_specializimit = resultSet.getString("emri_specializimit");
        return new Specializimet(id,emri_specializimit);
    }

    public int getId() {
        return id;
    }

    public String getEmri_specializimit() {
        return emri_specializimit;
    }
}
