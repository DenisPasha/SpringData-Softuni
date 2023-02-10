package DBAppsExercise.exercise;

import DBAppsExercise.exercise.utils.ColumnLabels;
import DBAppsExercise.exercise.utils.Constants;
import DBAppsExercise.exercise.utils.DbConnector;
import DBAppsExercise.exercise.utils.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getVilianNames {
    private final static String GET_VILLAIN_NAME_AND_MINIONS_QUERY = "select name ,  count(distinct mv.minion_id) minion_count\n" +
            " from villains v JOIN minions_villains mv on v.id= mv.villain_id\n" +
            " group by v.id\n" +
            " having minion_count > 15 \n" +
            " order by minion_count desc";

    public static void main(String[] args) throws SQLException {

        Connection connection = DbConnector.getDBConnection();

        PreparedStatement statement = connection.prepareStatement(GET_VILLAIN_NAME_AND_MINIONS_QUERY);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString(ColumnLabels.COLUMN_LABEL_NAME);
            int count = resultSet.getInt(ColumnLabels.COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf(Messages.PRINT_FORMAT,name , count);
        }
    }

}
