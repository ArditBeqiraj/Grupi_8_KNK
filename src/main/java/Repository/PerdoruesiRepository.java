package Repository;

import Database.DBConnector;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.UpdatePerdoruesiDTO;

import java.sql.*;
import java.util.ArrayList;

public class PerdoruesiRepository extends BaseRepository<Perdoruesi, CreatePerdoruesiDTO, UpdatePerdoruesiDTO> {

    public PerdoruesiRepository() {
        super("perdoruesi");
    }

    public Perdoruesi fromResultSet(ResultSet result) throws SQLException {
        return Perdoruesi.getInstance(result);
    }

    public ArrayList<Perdoruesi> getAllStaf() {
        ArrayList<Perdoruesi> users = new ArrayList<>();
        String query = "SELECT * FROM perdoruesi WHERE roli IN ('Doktor', 'Moter', 'Praktikant')";
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                users.add(Perdoruesi.getInstance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Perdoruesi getByEmail(String email) {
        String query = "SELECT * FROM perdoruesi WHERE email = ? LIMIT 1";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, email);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                return fromResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Perdoruesi create(CreatePerdoruesiDTO perdoruesiDTO) {
        String querySetSeq = "SELECT setval('id_seq', COALESCE((SELECT MAX(id) FROM perdoruesi), 230756100) + 1, false)";
        String queryInsert = """
            INSERT INTO perdoruesi (emri, mbiemri, email, telefoni, roli, hashedpassword, salt, krijuar)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Statement seqStmt = this.connection.createStatement();
             PreparedStatement pstm = this.connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)) {

            seqStmt.execute(querySetSeq);

            pstm.setString(1, perdoruesiDTO.getEmri());
            pstm.setString(2, perdoruesiDTO.getMbiemri());
            pstm.setString(3, perdoruesiDTO.getEmail());
            pstm.setString(4, perdoruesiDTO.getTelefoni());
            pstm.setString(5, perdoruesiDTO.getRoli());
            pstm.setString(6, perdoruesiDTO.getPassword());
            pstm.setString(7, perdoruesiDTO.getSalt());
            pstm.setTimestamp(8, perdoruesiDTO.getKrijuar());

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

    public Perdoruesi update(UpdatePerdoruesiDTO perdoruesiDTO) {
        String query = """
            UPDATE perdoruesi
            SET email = ?, telefoni = ?
            WHERE id = ?
        """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, perdoruesiDTO.getEmail());
            pstm.setString(2, perdoruesiDTO.getTelefoni());
            pstm.setInt(3, perdoruesiDTO.getUser_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(perdoruesiDTO.getUser_id());
    }

    public boolean updatePassword(int id, String hashedPassword, String salt) {
        String query = "UPDATE perdoruesi SET salt = ?, hashedpassword = ? WHERE id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, salt);
            pstm.setString(2, hashedPassword);
            pstm.setInt(3, id);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
