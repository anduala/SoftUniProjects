package IntroDBSapi.Exc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class InitialSetup {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","5562330");

        final String connectionURL = "jdbc:mysql://localhost:3306/minions_db";

        Connection connect =
                DriverManager.getConnection(connectionURL,properties);

        String query = "SELECT v.name, COUNT(mv.minion_id) AS `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > ?\n" +
                "ORDER BY `count` DESC;";

        PreparedStatement preparedStatement = connect
                .prepareStatement(query);

        preparedStatement.setInt(1,15);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            System.out.printf("%s %d%n",result.getString("name"), result.getInt("count"));
        }

        connect.close();
    }
}
