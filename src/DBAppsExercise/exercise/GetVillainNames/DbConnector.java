package DBAppsExercise.exercise.GetVillainNames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public enum DbConnector {
    ;

    public static Connection getDBConnection() throws SQLException {

        Properties props = new Properties();

        props.setProperty(Constants.USERNAME_KEY, Constants.USERNAME_VALUE);
        props.setProperty(Constants.PASSWORD_KEY, Constants.PASSWORD_VALUE);

         Connection myConnection = DriverManager.getConnection(Constants.DB_CONNECTION_lINK , props);
         return myConnection
         ;
    }

}

