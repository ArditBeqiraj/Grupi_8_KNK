package Repository;

import model.Specializimet.Specializimet;
import model.Specializimet.SpecializimetDTO.CreateSpecializimetDTO;
import model.Specializimet.SpecializimetDTO.UpdateSpecializimetDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpecializimetRepository extends BaseRepository<Specializimet, CreateSpecializimetDTO, UpdateSpecializimetDTO>{
    public SpecializimetRepository () {
        super("Specializimet");
    }

    public Specializimet fromResultSet(ResultSet result) throws SQLException {
        return Specializimet.getInstance(result);
    }

    public Specializimet create(CreateSpecializimetDTO specializimetDto) {
        String query = """
                INSERT INTO Specializimet (emri_specializimit)
                VALUES (?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setString(1, specializimetDto.getEmri_specializimit());
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

    public Specializimet update(UpdateSpecializimetDTO updateSpecializimiDto) {
        String query = """
                UPDATE Specializimet
                SET emri_specializimit = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updateSpecializimiDto.getEmri_specializimit());
            pstm.setInt(2, updateSpecializimiDto.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(updateSpecializimiDto.getId());
    }

}
