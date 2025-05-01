package Repository;

import Database.DBConnector;
import model.Faturimi.Faturimi;
import model.Inventari.Inventari;
import model.Inventari.InventariDTO.CreateInventariDTO;
import model.Inventari.InventariDTO.UpdateInventariDTO;

import java.sql.*;
import java.util.ArrayList;

public class InventariRepository extends BaseRepository<Inventari, CreateInventariDTO, UpdateInventariDTO>{

    public InventariRepository () {
        super("Inventari");
    }

    public Inventari fromResultSet(ResultSet result) throws SQLException{
        return Inventari.getInstance(result);
    }

    public Inventari create(CreateInventariDTO inventariDto) {
        String query = """
                INSERT INTO Inventari (emri_produktit, sasia, furnizuesi)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setString(1, inventariDto.getEmri_produktit());
            pstm.setDouble(2, inventariDto.getSasia());
            pstm.setString(3, inventariDto.getFurnizuesi());
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

    public Inventari update(UpdateInventariDTO inventariDto) {
        String query = """
                UPDATE Inventari
                SET sasia = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, inventariDto.getSasia());
            pstm.setInt(2, inventariDto.getProdukt_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(inventariDto.getProdukt_id());
    }
}
