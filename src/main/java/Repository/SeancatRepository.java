package Repository;

import Database.DBConnector;
import model.Rekordet.Rekordet;
import model.Seancat.Seancat;
import model.Seancat.SeancatDTO.CreateSeancaDTO;
import model.Seancat.SeancatDTO.UpdateSeancaDTO;

import java.sql.*;
import java.util.ArrayList;

public class SeancatRepository extends BaseRepository<Seancat, CreateSeancaDTO, UpdateSeancaDTO>{
    public SeancatRepository () {
        super("Seancat");
    }

    public Seancat fromResultSet(ResultSet result) throws SQLException{
        return Seancat.getInstance(result);
    }

    public Seancat create(CreateSeancaDTO seancat) {
        String query = """
                INSERT INTO Seancat (pacient_id, doktor_id, data_seances, statusi, shenime)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, seancat.getPacient_id());
            pstm.setInt(2, seancat.getDoktor_id());
            pstm.setTimestamp(3, seancat.getData_seances());
            pstm.setString(4, seancat.getStatusi());
            pstm.setString(5, seancat.getShenime());
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

    public Seancat update(UpdateSeancaDTO seancatDto) {
        String query = """
                UPDATE Seancat
                SET data_seances = ?,
                    statusi = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setTimestamp(1, seancatDto.getData_seances());
            pstm.setString(2, seancatDto.getStatusi());
            pstm.setInt(3, seancatDto.getSeanca_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(seancatDto.getSeanca_id());
    }
}
