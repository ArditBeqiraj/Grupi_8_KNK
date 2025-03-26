package test;
import java.sql.*;
import java.util.List;

import Database.DBConnector;
import model.Perdoruesi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class testDBConnector {
    public static void main(String[] args) throws SQLException {
        Connection connection=DBConnector.getConnection();
        try {
            if (connection != null && connection.isValid(1000)) {
                System.out.println("DB connected!");
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from grupi_8_knk.public.perdoruesi ;");

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String emri = resultSet.getString("emri");
                String mbiemri = resultSet.getString("mbiemri");

                System.out.println(id + " " + emri + " " + mbiemri);
            }

        } catch (SQLException e) {
                // Enhanced exception logging
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
