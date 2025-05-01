package Repository;

import Database.DBConnector;
import model.Pacientet.PacientetDTO.UpdatePacientetDTO;
import model.Perdoruesi.Perdoruesi;
import model.Rekordet.Rekordet;
import model.Rekordet.RekordetDTO.CreateRekordDTO;
import model.Rekordet.RekordetDTO.UpdateRekordDTO;

import java.sql.*;
import java.util.ArrayList;

public class RekordetRepository extends BaseRepository<Rekordet, CreateRekordDTO, UpdateRekordDTO> {
    public RekordetRepository () {
        super("Rekordet");
    }

    public Rekordet fromResultSet(ResultSet result) throws SQLException{
        return Rekordet.getInstance(result);
    }

    public Rekordet create(CreateRekordDTO rekordetDto) {
        //Kur kemi input nga useri vendosim ?
        String query2 = "SELECT setval('rekordet_rekord_id_seq', COALESCE((SELECT MAX(rekord_id) FROM rekordet), 630756100) + 1, false);";
        String query = """
                INSERT INTO Rekordet (pacient_id, doktor_id, diagnoza, udhezimi, data_vizites)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, rekordetDto.getPacient_id());
            pstm.setInt(2, rekordetDto.getDoktor_id());
            pstm.setString(3, rekordetDto.getDiagnoza());
            pstm.setString(4, rekordetDto.getUdhezimi());
            pstm.setDate(5, rekordetDto.getData_vizites());
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

    public Rekordet update(UpdateRekordDTO updateRekordDTO) {
        return null;
    }

}
