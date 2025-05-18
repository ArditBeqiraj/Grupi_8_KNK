package model.MjekuSpecializimi;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MjekuSpecializimi {
    private int id;
    private int mjeku_id;
    private String emri;
    private String mbiemri;
    private int specializimi_id;
    private String specializimi_kryesor;

    public MjekuSpecializimi(int id, String emri,String mbiemri,String specializimi_kryesor) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.specializimi_kryesor = specializimi_kryesor;
    }
    public static MjekuSpecializimi getInstance(ResultSet resultSet) throws SQLException{
        int id= resultSet.getInt("id");
        String emri= resultSet.getString("emri");
        String mbiemri= resultSet.getString("mbiemri");
        String specializimi_kryesor= resultSet.getString("specializimi_kryesor");

        return new MjekuSpecializimi(id,emri,mbiemri,specializimi_kryesor);
    }

    public int getId() {
        return id;
    }

    public int getMjeku_id() {
        return mjeku_id;
    }

    public int getSpecializimi_id() {
        return specializimi_id;
    }
    public String getSpecializimi(){
        return specializimi_kryesor;
    }
    public String toString(){
        return emri+ " " + mbiemri;
    }
    public String getEmri(){
        return emri;
    }
    public String getMbiemri(){
        return mbiemri;
    }
}

