package exception.database.table;

public class TableDoesNotExist extends RuntimeException {


    public TableDoesNotExist(String message) {
        super(message);
    }
}
