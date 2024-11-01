package exception.database.database;

public class DatabaseAlreadyExistsException extends RuntimeException {
    public DatabaseAlreadyExistsException(String message) {
        super(message);
    }
}