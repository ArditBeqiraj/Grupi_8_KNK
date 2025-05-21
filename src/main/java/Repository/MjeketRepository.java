package Repository;

import app.SessionManager;
import model.Mjeket.Mjeket;
import model.Mjeket.MjeketDTO.CreateMjeketDTO;
import model.Mjeket.MjeketDTO.UpdateMjeketDTO;
import model.Perdoruesi.Perdoruesi;

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

    public Mjeket getByEmail(String email) {
        String query = "SELECT * FROM mjeket WHERE email = ? LIMIT 1";
        try (
                PreparedStatement pst = this.connection.prepareStatement(query)) {

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

    public int totalPatientsfromDoctor() {
        String sql = "SELECT COUNT(id) AS total FROM pacientet WHERE mjeku_familjar_id = ?";
        int countData = 0;

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, SessionManager.getMjeku().getId());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                countData = result.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countData;
    }

    public int totalAppointmentsfromDoctor() {
        String sql = "SELECT COUNT(id) AS total FROM terminet WHERE id_mjek = ?";
        int countData = 0;

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, SessionManager.getMjeku().getId());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                countData = result.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countData;
    }

    public boolean update(Mjeket mjeku) {
        String query = "UPDATE mjeket SET emri = ?, mbiemri = ?, nr_licenses = ?, nr_telefoni = ?, email = ?, specializimi_kryesor = ? WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mjeku.getEmri());
            stmt.setString(2, mjeku.getMbiemri());
            stmt.setString(3, mjeku.getNr_licences());
            stmt.setString(4, mjeku.getNr_telefoni());
            stmt.setString(5, mjeku.getEmail());
            stmt.setString(6, mjeku.getSpecializimi_kryesor());
            stmt.setInt(7, mjeku.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
