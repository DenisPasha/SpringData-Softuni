package DBAppsExercise.exercise;

import DBAppsExercise.exercise.GetVillainNames.Constants;
import DBAppsExercise.exercise.GetVillainNames.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

public class ChangeTownNameCasting {

    private final static String UPDATE_TOWNS = "update towns\n" +
            " set name = upper(name)\n" +
            " where country = ? ";

    private final static String GET_TOWNS_BY_COUNTRY_NAME = "select name from towns where country = ? ";
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);


        Connection connection = DbConnector.getDBConnection();
        PreparedStatement updateTownsStmt = connection.prepareStatement(UPDATE_TOWNS);
        String countryInput = scanner.nextLine();
        updateTownsStmt.setString(1,countryInput);

        int i = updateTownsStmt.executeUpdate();

        if (i==0){
            System.out.println(Constants.NO_TOWNS_WERE_AFFECTED);
            return;
        }

        PreparedStatement getTownsStmt = connection.prepareStatement(GET_TOWNS_BY_COUNTRY_NAME);
        getTownsStmt.setString(1,countryInput);
        ResultSet townsSet = getTownsStmt.executeQuery();

        List<String> townsList = new ArrayList<>();

        while (townsSet.next()){
            townsList.add(townsSet.getString(Constants.COLUMN_LABEL_NAME));
        }
        System.out.printf(Constants.COUNT_OF_AFFECTED_TOWNS_FORMAT,townsList.size());
        System.out.println(townsList);

    }
}