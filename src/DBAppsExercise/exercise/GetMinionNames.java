package DBAppsExercise.exercise;

import DBAppsExercise.exercise.utils.ColumnLabels;
import DBAppsExercise.exercise.utils.Constants;
import DBAppsExercise.exercise.utils.DbConnector;
import DBAppsExercise.exercise.utils.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    private static final String GET_MINION_NAMES_BY_VILLAIN_ID = "select m.name , m.age\n" +
            "from minions m join minions_villains mv on m.id = mv.minion_id join villains v on v.id = mv.villain_id\n" +
            "where v.id = ?";


    private static final String GET_VILLAIN_NAME_BY_ID = "select name from villains where id = ?";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection connection = DbConnector.getDBConnection();

        PreparedStatement minionsStatements = connection.prepareStatement(GET_MINION_NAMES_BY_VILLAIN_ID);

        int index = Integer.parseInt(scanner.nextLine());
        minionsStatements.setInt(1,index);

        ResultSet minionsResultSet = minionsStatements.executeQuery();
        if (!minionsResultSet.next()){
            System.out.printf(Messages.NO_VILLAIN_WITH_GIVEN_ID_FORMAT,index);
            return;
        }

        PreparedStatement getVillainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        getVillainStatement.setInt(1,index);
        ResultSet villainResulSet = getVillainStatement.executeQuery();
        villainResulSet.next();
        String villainName = villainResulSet.getString(ColumnLabels.COLUMN_LABEL_NAME);

        System.out.printf(Messages.VILLAIN_NAME_FORMAT,villainName);

        int minionCount = 1;


        while (minionsResultSet.next()){
            String minionName = minionsResultSet.getString(ColumnLabels.COLUMN_LABEL_NAME);
            int minionAge = minionsResultSet.getInt(ColumnLabels.COLUMN_LABEL_AGE);

            System.out.printf(Messages.MINION_NAME_AND_AGE_FORMAT,minionCount,minionName,minionAge);

            minionCount++;
        }

    }

}
