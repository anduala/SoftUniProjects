

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            //2.  Get Villains’ Names
            //this.getVillainsNames();

            //3.  Get Minion Names
            //this.getMinionsNames();

            //4.  Add Minion
            //this.addingMinion();

            //5.  Change Town Names Casing
            //this.getTownCasing();

            //6.  Remove Villain
            //this.removeVillain();


            //7. Print All Minion Names
            //this.printAllMinionsNames();


            //.8  Increase Minions Age
            //this.increaseAge();


            //9.  Increase Age Stored Procedure
            this.increaseAgeStoredProcedure();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //2.  Get Villains’ Names
    private void getVillainsNames() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) AS `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > ?\n" +
                "ORDER BY `count` DESC;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,15);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            System.out.printf("%s %d%n",result.getString("name"), result.getInt("count"));
        }
    }


    //3.  Get Minion Names
    private void getMinionsNames() throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int id = Integer.parseInt(bufferedReader.readLine());

        String query = "SELECT m.name AS minion_name ,m.age AS minion_age,m.id,v.name AS villain FROM minions AS m\n" +
                " JOIN minions_villains AS mv\n" +
                " ON m.id = mv.minion_id\n" +
                " JOIN villains AS v \n" +
                " ON mv.villain_id = v.id\n" +
                " WHERE mv.villain_id = ?\n" +
                " GROUP BY mv.minion_id\n";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        int position = 1;
        if (!resultSet.next()){
            System.out.printf("No villain with ID %d exists in the database.",id);
        } else {
            System.out.printf("Villain: %s%n",resultSet.getString("villain"));
            System.out.printf("%d. %s %d%n",position,resultSet.getString("minion_name"),resultSet.getInt("minion_age"));
            position++;
            while (resultSet.next()){
                System.out.printf("%d. %s %d%n",position,resultSet.getString("minion_name"),resultSet.getInt("minion_age"));
                position++;
            }
        }
    }



    //4. Add Minion
    public void addingMinion() throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = bufferedReader.readLine().split("\\s+");
        String[] input2 = bufferedReader.readLine().split("\\s+");

        int idOfTown = 0;
        int idOfVillain = 0;
        int idOfMinion = 0;


        String minionName = input1[1];
        int minionAge = Integer.parseInt(input1[2]);
        String minionTown = input1[3];
        String villainName = input2[1];


        String queryForTownName = "SELECT id, name FROM towns WHERE name = ?";
        ResultSet resultSetTownName = getResultSet(minionTown, queryForTownName);
        String queryOfVillainsName = "SELECT id, name FROM minions_db.villains WHERE name = ?";
        ResultSet resultSetVillainsName = getResultSet(villainName, queryOfVillainsName);


        if (!resultSetTownName.isBeforeFirst()) {
            String queryInsertTownName = "INSERT towns SET name = ?";

            changeDatabase(minionTown, queryInsertTownName);

            System.out.printf("Town %s was added to the database.%n", minionTown);
        }



        if (!resultSetVillainsName.isBeforeFirst()) {
            String queryInsertVillainName = "INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil')";

            changeDatabase(villainName, queryInsertVillainName);

            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        resultSetTownName = getResultSet(minionTown, queryForTownName);
        resultSetVillainsName = getResultSet(villainName, queryOfVillainsName);

        resultSetTownName.next();
        idOfTown = resultSetTownName.getInt(1);

        resultSetVillainsName.next();
        idOfVillain = resultSetVillainsName.getInt(1);



        String queryAddingMinion = "INSERT INTO minions (name, age, town_id) VALUE (?, ?, ?)";
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(queryAddingMinion);
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, minionAge);
        preparedStatement.setInt(3, idOfTown);
        preparedStatement.executeUpdate();


        String querySelectingMinion = "SELECT id, name FROM minions WHERE name = ?";
        ResultSet resultSetMinionName = getResultSet(minionName, querySelectingMinion);
        while (resultSetMinionName.next()) {
            idOfMinion = resultSetMinionName.getInt(1);
        }

        String queryMinionToVillains = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);";
        preparedStatement = connection.prepareStatement(queryMinionToVillains);
        preparedStatement.setInt(1, idOfMinion);
        preparedStatement.setInt(2, idOfVillain);
        preparedStatement.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }


    //5. Change Town Names Casing
    private void getTownCasing() throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String country = bufferedReader.readLine();

        String query = "SELECT UPPER(t.name) AS town_name,t.country FROM towns AS t " +
                " WHERE t.country = ?" +
                " GROUP BY t.id";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,country);

        ResultSet resultSet = preparedStatement.executeQuery();


        if (!resultSet.next()){
            System.out.println("No town names were affected.");
        } else {
            int count = 1;
            while (resultSet.next()){
                count++;
            }
            resultSet.first();
            System.out.printf("%d town names were affected.%n",count);
            System.out.print("[");
            System.out.printf("%s",resultSet.getString("town_name"));
            while (resultSet.next()){
                System.out.printf(", %s",resultSet.getString("town_name"));
            }
            System.out.println("]");
        }

    }


    //6. Remove Villain
    private void removeVillain() throws SQLException, IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String id = bufferedReader.readLine();

        String query = " SELECT v.name AS villain_name FROM villains AS v" +
                " JOIN minions_villains AS mv " +
                " ON v.id = mv.villain_id" +
                " WHERE v.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        changeDatabase(id, "DELETE FROM minions_villains WHERE villain_id = ?");
        changeDatabase(id, "DELETE FROM villains WHERE id = ?");

        int position = 1;
        if (!resultSet.next()) {
            System.out.println("No such villain was found");
        } else {

            int count = 1;
            while (resultSet.next()){
                count++;
            }
            resultSet.first();
            System.out.printf("%s was deleted%n",resultSet.getString("villain_name"));
            System.out.printf("%d minions released",count);
        }

    }



    //7. Print All Minion Names
    public void printAllMinionsNames() throws SQLException, IOException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM minions_db.minions");
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

        for (String names : result) {
            System.out.println(names);
        }

    }


    //8. Increase Minions Age
    public void increaseAge() throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] idToChange = bufferedReader.readLine().split("\\s+");
        String updateMinion = "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id = ?";
        ResultSet resultSet;
        Statement statement = connection.createStatement();

        for (String s : idToChange) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(updateMinion);
            preparedStatement.setString(1, s);
            preparedStatement.executeUpdate();
        }

        resultSet = statement.executeQuery("SELECT name, age FROM minions_db.minions");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
        }
    }


    //9. Increase Age Stored Procedure
    public void increaseAgeStoredProcedure() throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.parseInt(bufferedReader.readLine());
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareCall("{call usp_get_older(?)}");
        preparedStatement.setInt(1, minionId);
        preparedStatement.execute();

        String query = "SELECT min.name, min.age FROM minions_db.minions AS min" +
                " WHERE min.id = ?";


        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,minionId);

        resultSet = preparedStatement.executeQuery();






        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
        }
    }


    //Methods


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
