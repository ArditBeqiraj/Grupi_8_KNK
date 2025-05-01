package Repository;

import Database.DBConnector;
import model.Faturimi.Faturimi;
import model.Orari.Orari;
import model.Orari.OrariDTO.CreateOrariDTO;
import model.Orari.OrariDTO.UpdateOrariDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrariRepository extends BaseRepository<Orari, CreateOrariDTO, UpdateOrariDTO>{
    public OrariRepository () {
        super("Orari");
    }

    public Orari fromResultSet(ResultSet result) throws SQLException{
        return Orari.getInstance(result);
    }

    public Orari create(CreateOrariDTO orariDto) {
        String query = """
                INSERT INTO Orari (perdoruesi_id, fillimi_ndrrimit, perfundimi_ndrrimit, roli)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, orariDto.getPerdoruesi_id());
            pstm.setTimestamp(2, orariDto.getFillimi_ndrrimit());
            pstm.setTimestamp(3, orariDto.getPerfundimi_ndrrimit());
            pstm.setString(4, orariDto.getRoli());
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

    public Orari update(UpdateOrariDTO orariDto) {
        String query = """
                UPDATE Orari
                SET fillimi_ndrrimit = ?,
                    perfundimi_ndrrimit = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setTimestamp(1, orariDto.getFillimi_ndrrimit());
            pstm.setTimestamp(2, orariDto.getPerfundimi_ndrrimit());
            pstm.setInt(3, orariDto.getOrari_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(orariDto.getOrari_id());
    }
}
