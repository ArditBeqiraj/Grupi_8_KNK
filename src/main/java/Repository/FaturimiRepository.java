package Repository;

import Database.DBConnector;
import model.Faturimi.Faturimi;
import model.Faturimi.FaturimiDTO.CreateFaturaDTO;
import model.Faturimi.FaturimiDTO.UpdateFaturaDTO;

import java.sql.*;
import java.util.ArrayList;

public class FaturimiRepository extends BaseRepository<Faturimi, CreateFaturaDTO, UpdateFaturaDTO>{
    public FaturimiRepository () {
        super("Faturimi");
    }

    public Faturimi fromResultSet(ResultSet result) throws SQLException{
        return Faturimi.getInstance(result);
    }

    public Faturimi create(CreateFaturaDTO faturaDto) {
        //Kur kemi input nga useri vendosim ?
        String query2 = "SELECT setval('faturimi_fatura_id_seq', COALESCE((SELECT MAX(fatura_id) FROM faturimi), 130756100) + 1, false);";
        String query = """
                INSERT INTO Faturimi (pacient_id, sherbimi_id, shuma, statusi, krijuar)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setInt(1, faturaDto.getPacient_id());
            pstm.setInt(2, faturaDto.getSherbimi_id());
            pstm.setDouble(3, faturaDto.getShuma());
            pstm.setString(4, faturaDto.getStatusi());
            pstm.setDate(5, faturaDto.getKrijuar());
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

    public Faturimi update(UpdateFaturaDTO faturaDto) {
        String query = """
                UPDATE Faturimi
                SET statusi = ?
                WHERE fatura_id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, faturaDto.getStatusi());
            pstm.setInt(2, faturaDto.getFatura_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(faturaDto.getFatura_id());
    }
}
