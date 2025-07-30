package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleSql {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
        final String DB_USERNAME = "postgres";
        final String DB_PASSWORD = "example";
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM alumno LIMIT 10");
            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
