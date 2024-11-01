package exception.database.table;

public class TableAlreadyExist extends RuntimeException {

    private static final String TABLE_ALREADY_EXISTS = "Table already exists for %s";

    public TableAlreadyExist(String tableName) {
        super(String.format(TABLE_ALREADY_EXISTS, tableName));
    }
}