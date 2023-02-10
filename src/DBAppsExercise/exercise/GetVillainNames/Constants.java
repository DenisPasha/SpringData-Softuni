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



}