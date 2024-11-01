package operator;

import java.util.Objects;

public class NotEqualsOperator extends Operator {

    private static NotEqualsOperator notEqualsOperator = new NotEqualsOperator();

    public static NotEqualsOperator getInstance() {
        return notEqualsOperator;
    }

    private NotEqualsOperator() {
        super(OperatorName.NOT_EQUAL);
    }

    @Override
    public boolean apply(Object actualValue, Object desiredValue) {

        //  return actualValue.equals(desiredValue);
        return Objects.equals(actualValue, desiredValue);
    }
}
