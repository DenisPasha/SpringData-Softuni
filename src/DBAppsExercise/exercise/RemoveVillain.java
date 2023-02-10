package DBAppsExercise.exercise;

import DBAppsExercise.exercise.GetVillainNames.Constants;
import DBAppsExercise.exercise.GetVillainNames.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    private final static String DELETE_ALL_MINIONS_OF_SERVING_GIVEN_VILLAIN = "delete mv\n" +
            "from minions_villains mv where villain_id = ?";

    private final static String DELETE_GIVEN_VILLAIN_FROM_DB = "delete v \n" +
            "from villains v where v.id = ?";

    private final static String GET_VILLAIN_NAME = "select name from villains where id = ? ";
    private final static String GET_MINIONS_COUNT = "select count(minion_id) as minions_count from minions_villains where villain_id = ? ";

    private final static String COLUMN_LABEL_MINIONS_COUNT= "minions_count";


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DbConnector.getDBConnection();
        int inputId = Integer.parseInt(scanner.nextLine());

        // GET VILLAIN NAME
        PreparedStatement getVillainNameStmt = connection.prepareStatement(GET_VILLAIN_NAME);
        getVillainNameStmt.setInt(1,inputId);
        ResultSet getVillainSet = getVillainNameStmt.executeQuery();

        if (!getVillainSet.next()) {
            System.out.printf(Constants.NO_SUCH_VILLAIN_FOUND);
            return;
        }
        String villainName = getVillainSet.getString(Constants.COLUMN_LABEL_NAME);


        // GET MINIONS COUNT
        PreparedStatement getMinionsCountStmt = connection.prepareStatement(GET_MINIONS_COUNT);
        getMinionsCountStmt.setInt(1,inputId);
        ResultSet getMinionsSet = getMinionsCountStmt.executeQuery();
        getMinionsSet.next();
        int minionsCount = getMinionsSet.getInt(COLUMN_LABEL_MINIONS_COUNT);


        // DELETE MINIONS OF SERVING
        PreparedStatement deleteFromServingStmt = connection.prepareStatement(DELETE_ALL_MINIONS_OF_SERVING_GIVEN_VILLAIN);

        deleteFromServingStmt.setInt(1,inputId);
        int i = deleteFromServingStmt.executeUpdate();

        // DELETE VILLAIN
        PreparedStatement deleteVillainStmt = connection.prepareStatement(DELETE_GIVEN_VILLAIN_FROM_DB);
        deleteVillainStmt.setInt(1,inputId);
        int affectedRows = deleteVillainStmt.executeUpdate();

        System.out.printf(Constants.DELETED_VILLAIN_FORMAT,villainName,minionsCount);



    }
}
