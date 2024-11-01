package exception.database.record;

public class RecordInvalidException extends RuntimeException {
    public RecordInvalidException(String message) {
        super(message);
    }

}
