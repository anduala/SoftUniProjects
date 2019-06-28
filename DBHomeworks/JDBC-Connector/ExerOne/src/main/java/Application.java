import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER = "root";
    private static final String PASSWORD = "5562330";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user",USER);
        properties.setProperty("password",PASSWORD);


        Connection connect =
                DriverManager.getConnection(CONNECTION_URL,properties);


        Engine engine = new Engine(connect);
        engine.run();
    }
}
