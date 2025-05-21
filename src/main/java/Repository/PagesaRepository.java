package Repository;

import Database.DBConnector;
import model.Pagesa.Pagesa;
import model.Pagesa.PagesaDTO.CreatePagesaDTO;
import model.Pagesa.PagesaDTO.UpdatePagesaDTO;

import java.sql.*;
import java.util.ArrayList;

public class PagesaRepository {
    private Connection connection;
    public PagesaRepository(){
        this.connection = DBConnector.getConnection();
    }
    public ArrayList<Pagesa> getAll(){
        ArrayList<Pagesa> list = new ArrayList<>();
        String query = "SELECT * FROM pagesa";
        try {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()){
                list.add(Pagesa.getInstance(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public Pagesa getById(int id) {
        String query = "SELECT * FROM pagesa WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return Pagesa.getInstance(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Pagesa create(CreatePagesaDTO dto){
        String query = "INSERT INTO pagesa (fatura_id, menyra_pageses, krijuar) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, dto.getPacient_id());
            if (dto.getFatura_id() != null) pstmt.setInt(2, dto.getFatura_id()); else pstmt.setNull(2, Types.INTEGER);
            pstmt.setBigDecimal(3, dto.getShuma());
            pstmt.setString(4, dto.getMenyra_pageses());
            pstmt.setString(5, dto.getStatusi());
            pstmt.execute();
            ResultSet res = pstmt.getGeneratedKeys();
            if (res.next()) {
                return getById(res.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Pagesa update(UpdatePagesaDTO dto){
        String column = null;
        Object value = null;
        if (dto.getShuma() != null) {
            column = "shuma";
            value = dto.getShuma();
        } else if (dto.getMenyra_pageses() != null) {
            column = "menyra_pageses";
            value = dto.getMenyra_pageses();
        } else if (dto.getStatusi() != null) {
            column = "statusi";
            value = dto.getStatusi();
        }

        if (column != null && value != null) {
            String query = "UPDATE pagesa SET " + column + " = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setObject(1, value);
                pstmt.setInt(2, dto.getid());
                int updated = pstmt.executeUpdate();
                if (updated == 1) {
                    return getById(dto.getid());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public boolean delete(int id){
        String query = "DELETE FROM pagesa WHERE id = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            return pstm.executeUpdate() == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
