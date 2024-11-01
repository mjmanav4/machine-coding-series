package models.constraint.string;

import models.constraint.Constraint;
import models.constraint.ConstraintName;

public class StringConstraint extends Constraint {

    private static StringConstraint stringConstraint = new StringConstraint();


    public static StringConstraint getInstance() {
        return stringConstraint;
    }

    private StringConstraint() {
        super(ConstraintName.TYPE_CHECK);
    }

    @Override
    public boolean validate(Object object) {
        return object.getClass().equals(String.class);
    }

}
