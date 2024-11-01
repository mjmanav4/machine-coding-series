package dto.query;

import operator.OperatorName;

public class ColFilterOperation {

    public ColFilterOperation(Object desiredValue, OperatorName operatorName) {
        this.desiredValue = desiredValue;
        this.operatorName = operatorName;
    }

    public Object getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(Object desiredValue) {
        this.desiredValue = desiredValue;
    }

    public OperatorName getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(OperatorName operatorName) {
        this.operatorName = operatorName;
    }

    Object desiredValue;

    OperatorName operatorName;
}
