package model.MjekuSpecializimi;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MjekuSpecializimi {
    private int id;
    private int mjeku_id;
    private int specializimi_id;

    private MjekuSpecializimi(int id, int mjeku_id, int specializimi_id) {
        this.id = id;
        this.mjeku_id = mjeku_id;
        this.specializimi_id = specializimi_id;
    }
    public static MjekuSpecializimi getInstance(ResultSet resultSet) throws SQLException{
        int id= resultSet.getInt("id");
        int mjeku_id = resultSet.getInt("mjeku_id");
        int specializimi_id = resultSet.getInt("specializimi_id");
        return new MjekuSpecializimi(id,mjeku_id,specializimi_id);
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
}

