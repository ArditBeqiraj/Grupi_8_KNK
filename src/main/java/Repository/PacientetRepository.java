package Repository;

import Database.DBConnector;
import model.Inventari.Inventari;
import model.Pacientet.Pacientet;
import model.Pacientet.PacientetDTO.CreatePacientetDTO;
import model.Pacientet.PacientetDTO.UpdatePacientetDTO;

import java.sql.*;
import java.util.ArrayList;

public class PacientetRepository extends BaseRepository<Pacientet, CreatePacientetDTO, UpdatePacientetDTO>{

    public PacientetRepository () {
        super("Pacientet");
    }

    public Pacientet fromResultSet(ResultSet result) throws SQLException{
        return Pacientet.getInstance(result);
    }

    public Pacientet create(CreatePacientetDTO pacientetDto) {
        String query = """
                INSERT INTO Pacientet (user_id, datelindja, gjinia, adresa, kontakti_emergjent)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, pacientetDto.getUser_id());
            pstm.setDate(2, pacientetDto.getDatelindja());
            pstm.setString(3, pacientetDto.getGjinia());
            pstm.setString(4, pacientetDto.getAdresa());
            pstm.setString(5, pacientetDto.getKontakti_emergjent());
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

    public Pacientet update(UpdatePacientetDTO pacientDto) {
        String query = """
                UPDATE Pacientet
                SET adresa = ?,
                    kontakti_emergjent = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, pacientDto.getAdresa());
            pstm.setString(2, pacientDto.getKontakti_emergjent());
            pstm.setInt(3, pacientDto.getPacient_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(pacientDto.getPacient_id());
    }
}
