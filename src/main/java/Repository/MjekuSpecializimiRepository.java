package Repository;

import model.MjekuSpecializimi.MjekuSpecializimi;
import model.MjekuSpecializimi.MjekuSpecializimiDTO.CreateMjekuSpecializimiDTO;
import model.MjekuSpecializimi.MjekuSpecializimiDTO.UpdateMjekuSpecializimiDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MjekuSpecializimiRepository extends BaseRepository<MjekuSpecializimi, CreateMjekuSpecializimiDTO, UpdateMjekuSpecializimiDTO>{
    public MjekuSpecializimiRepository() {
        super("MjekuSpecializimi");
    }

    public MjekuSpecializimi fromResultSet(ResultSet result) throws SQLException {
        return MjekuSpecializimi.getInstance(result);
    }

    public MjekuSpecializimi create(CreateMjekuSpecializimiDTO mjekuSpecializimiDto) {
        String query = """
                INSERT INTO Terminet (mjeku_id, specializimi_id)
                VALUES (?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, mjekuSpecializimiDto.getMjeku_id());
            pstm.setInt(2, mjekuSpecializimiDto.getSpecializimi_id());
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

    public MjekuSpecializimi update(UpdateMjekuSpecializimiDTO mjekuSpecializimiDto) {
        String query = """
                UPDATE MjekuSpecializimi
                SET specializimi_id = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, mjekuSpecializimiDto.getSpecializimi_id());
            pstm.setInt(2, mjekuSpecializimiDto.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(mjekuSpecializimiDto.getId());
    }
}
