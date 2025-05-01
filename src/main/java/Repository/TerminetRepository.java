package Repository;

import model.Terminet.Terminet;
import model.Terminet.TerminetDTO.CreateTerminetDTO;
import model.Terminet.TerminetDTO.UpdateTerminetDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TerminetRepository extends BaseRepository<Terminet, CreateTerminetDTO, UpdateTerminetDTO>{
    public TerminetRepository () {
        super("Terminet");
    }

    public Terminet fromResultSet(ResultSet result) throws SQLException {
        return Terminet.getInstance(result);
    }

    public Terminet create(CreateTerminetDTO terminetDto) {
        String query = """
                INSERT INTO Terminet (id_pacient, id_mjek, id_salle, data, statusi, shenime)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, terminetDto.getId_pacient());
            pstm.setInt(2, terminetDto.getId_mjek());
            pstm.setDouble(3, terminetDto.getId_salle());
            pstm.setDate(4, terminetDto.getData());
            pstm.setString(5, terminetDto.getStatusi());
            pstm.setString(6, terminetDto.getShenime());
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

    public Terminet update(UpdateTerminetDTO terminetDto) {
        String query = """
                UPDATE Terminet
                SET data = ?,
                    statusi = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setDate(1, terminetDto.getData());
            pstm.setString(2, terminetDto.getStatusi());
            pstm.setInt(3, terminetDto.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(terminetDto.getId());
    }
}
