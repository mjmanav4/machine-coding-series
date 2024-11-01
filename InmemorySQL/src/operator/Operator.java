package operator;


public abstract class Operator {

    public Operator(OperatorName operatorName) {
        this.operatorName = operatorName;
    }

    public OperatorName getOperatorName() {
        return operatorName;
    }

    private OperatorName operatorName;


   public abstract boolean apply(Object actualValue, Object desiredValue);
}
