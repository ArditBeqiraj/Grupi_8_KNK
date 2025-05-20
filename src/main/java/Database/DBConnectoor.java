package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectoor {
    private static Connection connection = null;

    private static final String url = "jdbc:postgresql://localhost:5432/KNK";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}