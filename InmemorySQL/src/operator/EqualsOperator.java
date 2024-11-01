package operator;

import java.util.Objects;

public class EqualsOperator extends Operator {

    private static EqualsOperator equalsOperator = new EqualsOperator();

    public static EqualsOperator getInstance() {
        return equalsOperator;
    }

    private EqualsOperator() {
        super(OperatorName.EQUAL);
    }

    @Override
    public boolean apply(Object actualValue, Object desiredValue) {

        //  return actualValue.equals(desiredValue);
        return Objects.equals(actualValue, desiredValue);

    }
}
