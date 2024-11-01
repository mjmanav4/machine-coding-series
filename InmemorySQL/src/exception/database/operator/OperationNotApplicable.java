package exception.database.operator;

public class OperationNotApplicable extends RuntimeException {
    public OperationNotApplicable(String message) {
        super(message);
    }

}
