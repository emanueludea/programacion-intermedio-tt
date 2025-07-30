import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        if (System.getenv("DB_PASSWORD") == null) {
            System.err.println("Error: DB_PASSWORD environment variable is not set.");
            return;
        }
        if (System.getenv("DB_HOST") == null) {
            System.err.println("Error: DB_HOST environment variable is not set.");
            return;
        }
        final String DB_HOST = System.getenv("DB_HOST");
        final String DB_PORT = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "5432";
        final String DB_NAME = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "postgres";
        final String DB_USERNAME = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
        final String DB_PASSWORD = System.getenv("DB_PASSWORD");
        final String DB_URL = String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME);
        System.out.println("Connecting to database at: " + DB_URL);
        System.out.println("Using username: " + DB_USERNAME);
        System.out.println("Using password: " + DB_PASSWORD.replaceAll(".", "*")); // Mask the password in logs
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
