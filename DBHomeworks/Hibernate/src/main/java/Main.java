import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException, IOException {


        Properties properties = new Properties();
        properties.setProperty("user", USER_NAME);
        properties.setProperty("password", PASSWORD);

        Connection connection = DriverManager.getConnection(CONNECTION_URL, properties);

        Homework homework = new Homework(connection);

        // Task 2 Get Villains’ Names
        homework.getVillainsNames();

        // Task 3. Get Minion Names
        // homework.getMinionNames();

        // Task 4. Add Minion
        // homework.addMinion();

        // Task 5. Change Town Names Casing
        // homework.changeTownNamesCasing();

        // Task 6. Remove Villain
        // homework.removeVillain();

        // Task 7. Print All Minion Names
        // homework.printAllMinionNames();

        // Task 8. Increase Minions Age
        // homework.increaseMinionsAge();

        // Task 9. Increase Age Stored Procedure
        // homework.increaseAgeStoredProcedure();
    }
}
