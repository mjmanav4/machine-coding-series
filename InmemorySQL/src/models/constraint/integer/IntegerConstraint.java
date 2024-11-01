package models.constraint.integer;

import models.constraint.Constraint;
import models.constraint.ConstraintName;

public class IntegerConstraint extends Constraint {

    private static IntegerConstraint integerConstraint = new IntegerConstraint();

    private IntegerConstraint() {
        super(ConstraintName.TYPE_CHECK);

    }

    public static IntegerConstraint getInstance() {
        return integerConstraint;
    }

    @Override
    public boolean validate(Object object) {
        if (object == null) {
            return false;
        }
        return object.getClass().equals(Integer.class);
    }
}
