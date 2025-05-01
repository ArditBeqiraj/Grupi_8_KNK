package Repository;

import Database.DBConnector;
import model.Seancat.Seancat;
import model.Sherbimet.Sherbimet;
import model.Sherbimet.SherbimetDTO.CreateSherbimDTO;
import model.Sherbimet.SherbimetDTO.UpdateSherbimDTO;

import java.sql.*;
import java.util.ArrayList;

public class SherbimetRepository extends BaseRepository<Sherbimet, CreateSherbimDTO, UpdateSherbimDTO>{
    public SherbimetRepository () {
        super("Sherbimet");
    }

    public Sherbimet fromResultSet(ResultSet result) throws SQLException{
        return Sherbimet.getInstance(result);
    }

    public Sherbimet create(CreateSherbimDTO sherbimDTO) {
        //Kur kemi input nga useri vendosim ?
        String query2 = "SELECT setval('sherbimet_sherbimi_id_seq', COALESCE((SELECT MAX(sherbimi_id) FROM sherbimet), 830756100) + 1, false);";
        String query = """
                INSERT INTO sherbimet (emri_sherbimit, pershkrimi, cmimi)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setString(1, sherbimDTO.getEmri_sherbimit());
            pstm.setString(2, sherbimDTO.getPershkrimi());
            pstm.setDouble(3, sherbimDTO.getCmimi());
            statement.executeQuery(query2);
            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Sherbimet update(UpdateSherbimDTO sherbimDto) {
        String query = """
                UPDATE Sherbimet
                SET cmimi = ?
                WHERE sherbimi_id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setDouble(1, sherbimDto.getCmimi());
            pstm.setInt(2, sherbimDto.getSherbimi_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(sherbimDto.getSherbimi_id());
    }

}
