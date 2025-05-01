package Repository;

import Database.DBConnector;
import model.Pacientet.Pacientet;
import model.Pacientet.PacientetDTO.UpdatePacientetDTO;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import model.Perdoruesi.PerdoruesiDTO.UpdatePerdoruesiDTO;

import java.sql.*;
import java.util.ArrayList;


public class PerdoruesiRepository extends BaseRepository<Perdoruesi, CreatePerdoruesiDTO, UpdatePerdoruesiDTO>{
    public PerdoruesiRepository () {
        super("perdoruesi");
    }

    public Perdoruesi fromResultSet(ResultSet result) throws SQLException{
        return Perdoruesi.getInstance(result);
    }

    public ArrayList<Perdoruesi> getAllStaf() {
        ArrayList<Perdoruesi> users = new ArrayList<>();
        String query = "SELECT * FROM perdoruesi WHERE roli IN ('Doktor', 'Moter', 'Praktikant')";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                users.add(
                        Perdoruesi.getInstance(resultSet)
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Perdoruesi getByEmail(String email){
        Connection conn= DBConnector.getConnection();
        String query = "SELECT * FROM Perdoruesi WHERE email = ? LIMIT 1";

        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                System.out.println("U gjet");
                return fromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public Perdoruesi create(CreatePerdoruesiDTO perdoruesiDTO) {
        //Kur kemi input nga useri vendosim ?
        String query2 = "SELECT setval('perdoruesi_id_seq', COALESCE((SELECT MAX(id) FROM perdoruesi), 230756100) + 1, false);";
        String query = """
                INSERT INTO Perdoruesi (emri, mbiemri, email, telefoni, roli, password, confirmpassword, krijuar)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS /*1*/);
            Statement statement = this.connection.createStatement();
            pstm.setString(1, perdoruesiDTO.getEmri());
            pstm.setString(2, perdoruesiDTO.getMbiemri());
            pstm.setString(3, perdoruesiDTO.getEmail());
            pstm.setString(4, perdoruesiDTO.getTelefoni());
            pstm.setString(5, perdoruesiDTO.getRoli());
            pstm.setString(6, perdoruesiDTO.getPassword());
            pstm.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
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

    public Perdoruesi update(UpdatePerdoruesiDTO perdoruesiDTO) {
        String query = """
                UPDATE Perdoruesi
                SET email = ?,
                    telefoni = ?,
                    password = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, perdoruesiDTO.getEmail());
            pstm.setString(2, perdoruesiDTO.getTelefoni());
            pstm.setString(3, perdoruesiDTO.getPassword());
            pstm.setInt(4, perdoruesiDTO.getUser_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(perdoruesiDTO.getUser_id());
    }
}
