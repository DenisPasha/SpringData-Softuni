package DBAppsExercise.exercise.GetVillainNames;

public enum Constants {
    ;

    static final String USERNAME_KEY = "user";
    static final String PASSWORD_KEY = "password";
    static final String USERNAME_VALUE  = "root";
    static final String PASSWORD_VALUE  = "125963Denis";
    static final String DB_CONNECTION_lINK  = "jdbc:mysql://localhost:3306/minions_db";

  public static final String COLUMN_LABEL_NAME = "name";
  public static final String COLUMN_LABEL_AGE = "age";
    static final String COLUMN_LABEL_MINIONS_COUNT = "minion_count";
    static final String PRINT_FORMAT = "%s %d%n";

   public static final String NO_VILLAIN_WITH_GIVEN_ID_FORMAT = "No villain with ID %d exists in the database.%n";
   public static final String VILLAIN_NAME_FORMAT = "Villain: %s%n";
   public static final String MINION_NAME_AND_AGE_FORMAT = "%d. %s %d%n";
   public static final String NO_TOWNS_WERE_AFFECTED = "No town names were affected.";
   public static final String COUNT_OF_AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";
   public static final String MINION_ADDED_TO_SERVE = "Successfully added %s to be minion of %s.%n";
   public static final String ADDED_TOWN_TO_DB = "Town %S was added to the database.%n";
   public static final String ADDED_VILLAIN_TO_DB = "Villain %s was added to the database.%n";
   public static final String COLUMN_LABEL_ID = "id";




}
