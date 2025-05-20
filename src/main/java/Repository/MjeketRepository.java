package Repository;

import model.Mjeket.Mjeket;
import model.Mjeket.MjeketDTO.CreateMjeketDTO;
import model.Mjeket.MjeketDTO.UpdateMjeketDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MjeketRepository extends BaseRepository<Mjeket, CreateMjeketDTO, UpdateMjeketDTO>{
    public MjeketRepository() {
        super("Mjeket");
    }

    public Mjeket fromResultSet(ResultSet result) throws SQLException {
        return Mjeket.getInstance(result);
    }

    public Mjeket create(CreateMjeketDTO mjeketDto) {
        String query = """
                INSERT INTO Mjeket (emri, mbiemri, nr_licenses, nr_telefoni, email, specializimi_kryesor)
                VALUES (?, ?, null, ?, ?, null)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            pstm.setString(1, mjeketDto.getEmri());
            pstm.setString(2, mjeketDto.getMbiemri());
            pstm.setString(3, mjeketDto.getNr_telefoni());
            pstm.setString(4, mjeketDto.getEmail());
            pstm.executeUpdate();
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
    public Mjeket update(UpdateMjeketDTO mjeketDto) {
        String query = """
                UPDATE Mjeket
                SET nr_telefoni = ?,
                    email = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, mjeketDto.getNr_telefoni());
            pstm.setString(2, mjeketDto.getEmail());
            pstm.setInt(3, mjeketDto.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(mjeketDto.getId());
    }
}
