package DBAppsExercise.exercise.utils;

public enum Messages {
    ;
    public static final String PRINT_FORMAT = "%s %d%n";
    public static final String NO_VILLAIN_WITH_GIVEN_ID_FORMAT = "No villain with ID %d exists in the database.%n";
    public static final String VILLAIN_NAME_FORMAT = "Villain: %s%n";
    public static final String MINION_NAME_AND_AGE_FORMAT = "%d. %s %d%n";
    public static final String NO_TOWNS_WERE_AFFECTED = "No town names were affected.";
    public static final String COUNT_OF_AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";
    public static final String MINION_ADDED_TO_SERVE = "Successfully added %s to be minion of %s.%n";
    public static final String ADDED_TOWN_TO_DB = "Town %S was added to the database.%n";
    public static final String ADDED_VILLAIN_TO_DB = "Villain %s was added to the database.%n";
    public static final String NO_SUCH_VILLAIN_FOUND = "No such villain was found%n";
    public static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n" +
            "%d minions released%n";
}
