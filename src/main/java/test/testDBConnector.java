package test;
import java.sql.*;

import Database.DBConnector;


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
