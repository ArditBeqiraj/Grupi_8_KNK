package Repository;

import Database.DBConnector;
import model.PajisjetMjekesore.PajisjetMjekesore;
import model.PajisjetMjekesore.PajisjetMjekesoreDTO.CreatePajisjetMjekesoreDTO;
import model.PajisjetMjekesore.PajisjetMjekesoreDTO.UpdatePajisjetMjekesoreDTO;

import java.sql.*;
import java.util.ArrayList;

public class PajisjetMjekesoreRepository {
    private Connection connection;
    public PajisjetMjekesoreRepository(){
        this.connection = DBConnector.getConnection();
    }
    public ArrayList<PajisjetMjekesore>getAll(){
        ArrayList<PajisjetMjekesore> pajisjet = new ArrayList<>();
        String query = "SELECT * FROM PajisjetMjekesore";
        try {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()){
                pajisjet.add(PajisjetMjekesore.getInstance(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pajisjet;
    }
    public PajisjetMjekesore getById(int id){
        String query = "SELECT * FROM PajisjetMjekesore WHERE pajisja_id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                return PajisjetMjekesore.getInstance(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public PajisjetMjekesore create(CreatePajisjetMjekesoreDTO dto){
        String query = "INSERT INTO PajisjetMjekesore (emri_pajisjes, pershkrimi, sasia, gjendja, salla_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri_pajisjes());
            pstm.setString(2, dto.getPershkrimi());
            pstm.setInt(3,dto.getSasia());
            pstm.setString(4, dto.getGjendja());
            if (dto.getSalla_id() != null){
                pstm.setInt(5,dto.getSalla_id());
            }else {
                pstm.setNull(5,Types.INTEGER);
            }
            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()){
                return getById(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public PajisjetMjekesore update(UpdatePajisjetMjekesoreDTO dto){
        String query = "UPDATE PajisjetMjekesore SET emri_pajisjes = ?, pershkrimi = ?, sasia = ?, gjendja = ?, salla_id = ? WHERE id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri_pajisjes());
            pstm.setString(2,dto.getPershkrimi());
            pstm.setInt(3,dto.getSasia());
            pstm.setString(4,dto.getGjendja());
            if (dto.getSalla_id() != null){
                pstm.setInt(5,dto.getSalla_id());
            }else {
                pstm.setNull(5,Types.INTEGER);

            }
            pstm.setInt(6,dto.getPajisja_id());
            int affected = pstm.executeUpdate();
            if (affected == 1){
                return getById(dto.getPajisja_id());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean delete(int id){
        String query = "DELETE FROM PajisjetMjekesore WHERE pajisja_id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            return pstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
