package DBAppsExercise.exercise;

import DBAppsExercise.exercise.GetVillainNames.Constants;
import DBAppsExercise.exercise.GetVillainNames.DbConnector;

import java.awt.desktop.PrintFilesEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {

    private final static String EVILNESS_FACTOR = "evil";

    private final static String GET_TOWN_NAME_BY_NAME_IF_EXIST = "select name from towns t where t.name = ? ";
    private final static String ADD_GIVEN_TOWN_TO_DB = "insert into towns (name) values (?)";
    private final static String GET_VILLAIN_BY_NAME_IF_EXIST = "select name from villains where name = ?";
    private final static String ADD_GIVEN_VILLAIN_TO_DB = "insert into villains (name , evilness_factor) values (? , ? )";
    private final static String GET_TOWN_ID_BY_TOWN_NAME = "select id from towns where name = ? ";
    private final static String ADD_MINION_TO_DB = "insert into minions (name , age , town_id ) values (? , ? , ? ) ";
    private final static String GET_MINION_ID_BY_NAME_AGE_TOWN = "select m.id from minions m join towns t on m.town_id = t.id \n" +
            " where m.name = ? and m.age = ? and t.name = ? ";

    private final static String ADD_MINION_TO_SERVE_VILLAIN = "insert into minions_villains (minion_id , villain_id) values (? , ?);";

    private final static String GET_VILLAIN_ID_BY_VILLAIN_NAME = "select id from villains where name = ?";
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        Connection connection = DbConnector.getDBConnection();
        PreparedStatement getTownIfExistStmt = connection.prepareStatement(GET_TOWN_NAME_BY_NAME_IF_EXIST);

        //Minion: Robert 14 Berlin
        String[] input = scanner.nextLine().split(" ");

        //Villain: Gru
        String villainNameInput = scanner.nextLine().split(" ")[1];

        String minionName = input[1];
        int minionAge = Integer.parseInt(input[2]);
        String townName = input[3];


        getTownIfExistStmt.setString(1,townName);
        ResultSet townResultSet = getTownIfExistStmt.executeQuery();


        if (!townResultSet.next()){
            // no town in DB
            //adding town do DB
            PreparedStatement addTownToDBStmt = connection.prepareStatement(ADD_GIVEN_TOWN_TO_DB);
            addTownToDBStmt.setString(1,townName);
            int i = addTownToDBStmt.executeUpdate();

            System.out.printf(Constants.ADDED_TOWN_TO_DB,townName);
        }

        PreparedStatement getVillainNameStmt = connection.prepareStatement(GET_VILLAIN_BY_NAME_IF_EXIST);
        getVillainNameStmt.setString(1,villainNameInput);
        ResultSet getVillainNameResultSet = getVillainNameStmt.executeQuery();

        if (!getVillainNameResultSet.next()){
            //no villain in DB
            //adding Villain to DB
            PreparedStatement addVillainToDBStmt = connection.prepareStatement(ADD_GIVEN_VILLAIN_TO_DB);
            addVillainToDBStmt.setString(1,villainNameInput);
            addVillainToDBStmt.setString(2,EVILNESS_FACTOR);
            int i = addVillainToDBStmt.executeUpdate();
            System.out.printf(Constants.ADDED_VILLAIN_TO_DB,villainNameInput);
        }

        //GET TOWN ID
        PreparedStatement getTownIDStmt = connection.prepareStatement(GET_TOWN_ID_BY_TOWN_NAME);
        getTownIDStmt.setString(1,townName);
        ResultSet townIDResultSet = getTownIDStmt.executeQuery();
        townIDResultSet.next();
        int townId = townIDResultSet.getInt(Constants.COLUMN_LABEL_ID);

        // INSERT MINION TO DB
        PreparedStatement addMinionsToDbStmt = connection.prepareStatement(ADD_MINION_TO_DB);
        addMinionsToDbStmt.setString(1,minionName);
        addMinionsToDbStmt.setInt(2,minionAge);
        addMinionsToDbStmt.setInt(3,townId);

        int i = addMinionsToDbStmt.executeUpdate();

        //GET ADDED MINION ID
        PreparedStatement getMinionIdStmt = connection.prepareStatement(GET_MINION_ID_BY_NAME_AGE_TOWN);
        getMinionIdStmt.setString(1,minionName);
        getMinionIdStmt.setInt(2,minionAge);
        getMinionIdStmt.setString(3,townName);
        ResultSet getMinionIdSet = getMinionIdStmt.executeQuery();
        getMinionIdSet.next();
        int minionId = getMinionIdSet.getInt("m.id");


        //GET VILLAIN ID
        PreparedStatement getVillainIdStmt = connection.prepareStatement(GET_VILLAIN_ID_BY_VILLAIN_NAME);
        getVillainIdStmt.setString(1,villainNameInput);
        ResultSet getVillainIdSet = getVillainIdStmt.executeQuery();
        getVillainIdSet.next();
        int villainId = getVillainIdSet.getInt(Constants.COLUMN_LABEL_ID);

        // ADDING THE NEWLY INSERTED MINION TO BE SERVANT OF THE GIVEN VILLAIN

        PreparedStatement addMinionToServeStmt = connection.prepareStatement(ADD_MINION_TO_SERVE_VILLAIN);
        addMinionToServeStmt.setInt(1,minionId);
        addMinionToServeStmt.setInt(2,villainId);
        int i1 = addMinionToServeStmt.executeUpdate();

        System.out.printf(Constants.MINION_ADDED_TO_SERVE,minionName, villainNameInput);
    }
}
