package exception.database.record;

public class RecordDoesNotExist extends RuntimeException {
    public RecordDoesNotExist(String message) {
        super(message);
    }

}
