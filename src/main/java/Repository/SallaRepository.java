package Repository;

import Database.DBConnector;
import model.Salla.Salla;
import model.Salla.SallaDTO.CreateSallaDTO;
import model.Salla.SallaDTO.UpdateSallaDTO;

import java.sql.*;
import java.util.ArrayList;

public class SallaRepository {
    private final Connection connection;
    public SallaRepository(){
        this.connection= DBConnector.getConnection();
    }
    public ArrayList<Salla> getAll(){
        ArrayList<Salla> sallat = new ArrayList<>();
        String query = "SELECT * FROM salla";
        try {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()){
                sallat.add(Salla.getInstance(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sallat;
    }
    public Salla getById(int id){
        String query = "SELECT * FROM salla WHERE id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                return Salla.getInstance(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Salla create(CreateSallaDTO dto){
        String query = """
                INSERT INTO salla (emri_salles, tipi, kapaciteti, statusi)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri_salles());
            pstm.setString(2,dto.getTipi());
            pstm.setInt(3,dto.getKapaciteti());
            pstm.setString(4,dto.getStatusi());
            pstm.executeUpdate();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()){
                return getById(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Salla update(UpdateSallaDTO dto){
        String query = """
                UPDATE salla
                SET emri_salles = ?, tipi = ?, kapaciteti = ?, statusi = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri_salles());
            pstm.setString(2, dto.getTipi());
            if (dto.getKapaciteti() != null){
                pstm.setInt(3, dto.getKapaciteti());
            }else {
                pstm.setNull(3,Types.INTEGER);
            }
            pstm.setString(4, dto.getStatusi());
            pstm.setInt(5, dto.getId());
            int updated = pstm.executeUpdate();
            if (updated == 1){
                return getById(dto.getId());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean delete(int id){
        String query = "DELETE FROM salla WHERE id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            return  pstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
