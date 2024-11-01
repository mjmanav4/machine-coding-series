package models.constraint.string;

import models.constraint.Constraint;
import models.constraint.ConstraintName;

public class StringLengthConstraint extends Constraint {

    private Integer minLength;

    private Integer maxLength;


    public StringLengthConstraint(Integer minLength, Integer maxLength) {
        super(ConstraintName.RANGE_CHECK);
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    @Override
    public boolean validate(Object object) {

        String value = (String) object;
        return value.length() >= this.minLength && value.length() <= this.maxLength;
    }
}
