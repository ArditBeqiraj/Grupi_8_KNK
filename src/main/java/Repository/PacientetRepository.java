package Repository;

import model.Pacientet.Pacientet;
import model.Pacientet.PacientetDTO.CreatePacientetDTO;
import model.Pacientet.PacientetDTO.UpdatePacientetDTO;
import model.Perdoruesi.Perdoruesi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientetRepository extends BaseRepository<Pacientet, CreatePacientetDTO, UpdatePacientetDTO> {

    public PacientetRepository() {
        super("Pacientet");
    }

    public Pacientet fromResultSet(ResultSet result) throws SQLException {
        return Pacientet.getInstance(result);
    }

    public Pacientet create(CreatePacientetDTO pacientetDto) {
        String query = """
                INSERT INTO Pacientet (user_id, datelindja, gjinia, adresa, kontakti_emergjent)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            pstm.setString(2, pacientDto.getKontaktiEmergjent());
            pstm.setInt(3, pacientDto.getPatientid());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(pacientDto.getPatientid());
    }


    public List<Pacientet> getAllWithPerdoruesiByDoctorId(int doctorId) {
        List<Pacientet> pacientetList = new ArrayList<>();

        String query = """
        SELECT pa.*, 
               p.emri, p.mbiemri, p.email, p.telefoni
        FROM Pacientet pa
        JOIN Perdoruesi p ON pa.user_id = p.id
        WHERE pa.mjeku_familjar_id = ?
        """;

        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setInt(1, doctorId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Pacientet pacient = Pacientet.getInstance(rs);

                    Perdoruesi perdoruesi = new Perdoruesi(
                            rs.getInt("user_id"),
                            rs.getString("emri"),
                            rs.getString("mbiemri"),
                            rs.getString("email"),
                            rs.getString("telefoni"),
                            null, null, null, null
                    );

                    pacient.setPerdoruesi(perdoruesi);
                    pacientetList.add(pacient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientetList;
    }

}
