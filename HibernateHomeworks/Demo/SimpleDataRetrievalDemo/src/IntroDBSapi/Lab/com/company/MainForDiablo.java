package IntroDBSapi.Lab.com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class MainForDiablo {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter username default (root): ");
        String user = bufferedReader.readLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty): ");
        String password = bufferedReader.readLine();
        System.out.println();

        Properties prop = new Properties();
        prop.setProperty("user",user);
        prop.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", prop);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT u.user_name,u.first_name, u.last_name, COUNT(ug.id) " +
                        "AS `count` FROM users AS u\n" +
                        "INNER JOIN users_games AS ug\n" +
                        "ON u.id = ug.user_id\n" +
                        "WHERE u.user_name = ?" +
                        "GROUP BY ug.user_id");

        String nameOfUser = bufferedReader.readLine();
        stmt.setString(1, nameOfUser);
        ResultSet resultSet = stmt.executeQuery();

        if(!resultSet.next()){
            System.out.println("No such user exists");
        } else {
            System.out.println("User: " + resultSet.getString("user_name"));
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name")
            + " has played " + resultSet.getString("count") + " games");

        }
        connection.close();
    }
}
