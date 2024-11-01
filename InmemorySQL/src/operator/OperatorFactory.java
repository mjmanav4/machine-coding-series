package operator;

import exception.database.operator.OperationNotApplicable;

public class OperatorFactory {

    private OperatorFactory operatorFactory = new OperatorFactory();

    private OperatorFactory() {
    }


    public static Operator getOperator(OperatorName operatorName) {
        switch (operatorName) {
            case EQUAL:
                return EqualsOperator.getInstance(); // Return EqualsOperator instance
            case NOT_EQUAL:
                return NotEqualsOperator.getInstance(); // Return NotEqualsOperator instance
            default:
                throw new OperationNotApplicable("Operator not applicable"); // Handle unknown operators
        }
    }
}
