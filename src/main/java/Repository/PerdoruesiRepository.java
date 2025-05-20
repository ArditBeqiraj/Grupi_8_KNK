package Repository;

import Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.Pacientet.Pacientet;
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

    public Perdoruesi create(CreatePerdoruesiDTO perdoruesiDTO) {
        String querySetSeq = "SELECT setval('id_seq', COALESCE((SELECT MAX(id) FROM perdoruesi), 230756100) + 1, false)";
        String queryInsert = """
            INSERT INTO perdoruesi (emri, mbiemri, email, telefoni, roli, salt, hashedpassword, krijuar)
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
            pstm.setString(6, perdoruesiDTO.getSalt());
            pstm.setString(7, perdoruesiDTO.getHashedpassword());
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

    public boolean emailExistsForOtherUser(String email, int userId) {
        String query = "SELECT 1 FROM perdoruesi WHERE email = ? AND ID != ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Perdoruesi update(Perdoruesi perdoruesi) {
        String query = "UPDATE perdoruesi SET emri = ?, mbiemri = ?, email = ?, telefoni = ?, roli = ? WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, perdoruesi.getEmri());
            stmt.setString(2, perdoruesi.getMbiemri());
            stmt.setString(3, perdoruesi.getEmail());
            stmt.setString(4, perdoruesi.getTelefoni());
            stmt.setString(5, perdoruesi.getRoli());
            stmt.setInt(6, perdoruesi.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getById(perdoruesi.getId());
    }

    public Perdoruesi update(UpdatePerdoruesiDTO updatePerdoruesiDTO) {
        return null;
    };


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

    public int totalEmployees() {
        String sql = "SELECT COUNT(id) AS total FROM perdoruesi WHERE roli IN ('Doktor', 'Moter', 'Praktikant')";
        int countData = 0;
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                countData = result.getInt("total");
            }
            return countData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int totalPacient() {
        String sql = "SELECT COUNT(id) AS total FROM pacientet";
        int countData = 0;
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                countData = result.getInt("total");
            }
            return countData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public XYChart.Series homeChart() {
        String sql = """
        SELECT DATE(krijuar) AS created_date, COUNT(id)
        FROM perdoruesi
        WHERE roli IN ('Doktor', 'Moter', 'Praktikant')
        GROUP BY created_date
        ORDER BY created_date ASC
        LIMIT 7
        """;
        try {
            XYChart.Series chart = new XYChart.Series();

            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            return chart;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Pacientet> getAllPacientet() throws SQLException {
        ObservableList<Pacientet> pacientList = FXCollections.observableArrayList();

        String query = """
            SELECT 
                p.id AS pacient_id,
                p.user_id,
                p.datelindja,
                p.gjinia,
                p.adresa,
                p.kontakti_emergjent,
                p.mjeku_familjar_id,
                u.id,
                u.emri,
                u.mbiemri,
                u.email,
                u.telefoni,
                u.roli,
                u.salt,
                u.hashedpassword,
                u.krijuar
            FROM 
                Pacientet p
            INNER JOIN 
                Perdoruesi u ON p.user_id = u.id
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Pacientet pacienti = Pacientet.getInstance(resultSet);
                Perdoruesi perdoruesi = Perdoruesi.getInstance(resultSet);
                pacienti.setPerdoruesi(perdoruesi);

                pacientList.add(pacienti);
            }
        }

        return pacientList;
    }
}