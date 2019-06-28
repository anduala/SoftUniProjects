import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Homework {

    private Connection connection;

    public Homework(Connection connection) {
        this.connection = connection;
    }

    // Task 2 Get Villains’ Names
    public void getVillainsNames() throws SQLException {
        String query = "select v.name, COUNT(mv.minion_id) count from villains v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "group by v.name\n" +
                "having count > ?\n" +
                "order by count desc;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 15);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2)).println();
        }
    }

    // Task 3. Get Minion Names
    public void getMinionNames() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String query = "select name from villains\n" +
                "where id = ?;";
        String query2 = "select m.name, m.age from minions m\n" +
                "join minions_villains mv on m.id = mv.minion_id\n" +
                "where mv.villain_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, n);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            System.out.printf("No villain with ID %s exists in the database.", n).println();
        }

        while (resultSet.next()) {
            System.out.printf("Villain: %s", resultSet.getString(1)).println();
        }

        int count = 1;

        preparedStatement = connection.prepareStatement(query2);
        preparedStatement.setInt(1, n);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%d. %s %d", count++, resultSet.getString(1), resultSet.getInt(2)).println();
        }
    }

    // Task 4. Add Minion
    public void addMinion() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split("\\s+");
        String[] line2 = br.readLine().split("\\s+");

        String minionName = line1[1];
        int minionAge = Integer.parseInt(line1[2]);
        String minionTowns = line1[3];
        String villainName = line2[1];
        int townId = 0;
        int villainId = 0;
        int minionId = 0;

        String queryTownName = "SELECT id, name FROM towns WHERE name = ?;";
        ResultSet resultSetTownName = getResultSet(minionTowns, queryTownName);
        String queryVillainsName = "SELECT id, name FROM minions_db.villains WHERE name = ?;";
        ResultSet resultSetVillainsName = getResultSet(villainName, queryVillainsName);


        // Add Town if not exist
        if (!resultSetTownName.isBeforeFirst()) {
            String queryInsertTownName = "INSERT towns set name = ?;";

            changeDatabase(minionTowns, queryInsertTownName);

            System.out.printf("Town %s was added to the database.", minionTowns).println();
        }


        // Add Villain if not exist

        if (!resultSetVillainsName.isBeforeFirst()) {
            String queryInsertVillainName = "INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil');";

            changeDatabase(villainName, queryInsertVillainName);

            System.out.printf("Villain %s was added to the database.", villainName).println();
        }

        // Find Minion and Villain id
        resultSetTownName = getResultSet(minionTowns, queryTownName);
        resultSetVillainsName = getResultSet(villainName, queryVillainsName);

        resultSetTownName.next();
        townId = resultSetTownName.getInt(1);

        resultSetVillainsName.next();
        villainId = resultSetVillainsName.getInt(1);


        //Add Minion to Minions Table
        String queryAddMinion = "INSERT INTO minions (name, age, town_id) VALUE (?, ?, ?);";
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(queryAddMinion);
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, minionAge);
        preparedStatement.setInt(3, townId);
        preparedStatement.executeUpdate();

        //Add Connection to minions_villains table
        String querySelectMinions = "SELECT id, name FROM minions WHERE name = ?;";
        ResultSet resultSetMinionName = getResultSet(minionName, querySelectMinions);
        while (resultSetMinionName.next()) {
            minionId = resultSetMinionName.getInt(1);
        }

        String queryMinionToVillains = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);";
        preparedStatement = connection.prepareStatement(queryMinionToVillains);
        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
    }

    // Task 5. Change Town Names Casing
    public void changeTownNamesCasing() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String country = br.readLine();
        List<String> result = new LinkedList<String>();
        int townsCount = 0;
        String querySelectTownsInCountry = "SELECT name FROM towns WHERE country = ?;";
        ResultSet resultSet = getResultSet(country, querySelectTownsInCountry);

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No town names were affected.");
        } else {

            String queryUpdateTownsInCountry = "UPDATE towns SET name = upper(name) WHERE country = ?;";
            changeDatabase(country, queryUpdateTownsInCountry);
            resultSet = getResultSet(country, querySelectTownsInCountry);

            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                townsCount++;
            }
            System.out.printf("%d town names were affected.", townsCount).println();
            System.out.println(result);
        }
    }

    // Task 6. Remove Villain
    public void removeVillain() throws IOException, SQLException {
        try {
            connection.setAutoCommit(false);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String villainId = br.readLine();
            ResultSet resultSet = getResultSet(villainId, "SELECT name FROM villains WHERE id = ?;");
            resultSet.next();
            String villainName = resultSet.getString(1);
            resultSet = getResultSet(villainId, "SELECT COUNT(*) FROM minions_villains WHERE villain_id = ?;");
            resultSet.next();
            int minionCount = resultSet.getInt(1);
            changeDatabase(villainId, "DELETE FROM minions_villains WHERE villain_id = ?;");
            changeDatabase(villainId, "DELETE FROM villains WHERE id = ?;");
            System.out.printf("%s was deleted", villainName).println();
            System.out.printf("%d minions released", minionCount).println();

            connection.commit();

        } catch (SQLException ex) {
            connection.rollback();
            System.out.println("No such villain was found");
        }

    }

    // Task 7. Print All Minion Names
    public void printAllMinionNames() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM minions_db.minions;");
        List<String> minionNames = new LinkedList<String>();
        List<String> result = new LinkedList<String>();

        while (resultSet.next()) {
            minionNames.add(resultSet.getString(1));
        }

        int n = 0;
        for (int i = 0; i < minionNames.size(); i++) {

            if (i % 2 == 0) {
                result.add(minionNames.get(n));
                n++;
            }else {
                result.add(minionNames.get(minionNames.size() - n));
            }
        }

        System.out.println(result);
    }

    // Task 8. Increase Minions Age
    public void increaseMinionsAge() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] minionIdToChange = br.readLine().split("\\s+");
        String updateMinion = "UPDATE minions SET age = age + 1, name = lower(name) WHERE id = ?;";
        ResultSet resultSet;
        Statement statement = connection.createStatement();

        for (String s : minionIdToChange) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(updateMinion);
            preparedStatement.setString(1, s);
            preparedStatement.executeUpdate();
        }

        resultSet = statement.executeQuery("SELECT name, age FROM minions_db.minions;");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
        }
    }

    // Task 9. Increase Age Stored Procedure
    public void increaseAgeStoredProcedure() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.parseInt(br.readLine());
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareCall("{call usp_get_older(?)}");
        preparedStatement.setInt(1, minionId);
        preparedStatement.execute();

        resultSet = preparedStatement.executeQuery("SELECT name, age FROM minions_db.minions;");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
        }
    }


    private void changeDatabase(String value, String query) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, value);
        preparedStatement.executeUpdate();
    }

    private ResultSet getResultSet(String value, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, value);
        return preparedStatement.executeQuery();
    }
}

