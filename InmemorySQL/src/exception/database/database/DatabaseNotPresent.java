package exception.database.database;


public class DatabaseNotPresent extends RuntimeException {

    public static  String DATABASE_NOT_PRESENT = "Database not present";

    private static final StringBuilder MESSAGE_TEMPLATE = new StringBuilder().append(DATABASE_NOT_PRESENT).append(" for ");

    public DatabaseNotPresent(String databaseName) {
        super(MESSAGE_TEMPLATE.append(databaseName).toString());
    }
}