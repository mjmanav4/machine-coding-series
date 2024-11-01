package models.constraint.integer;

import models.constraint.Constraint;
import models.constraint.ConstraintName;

public class IntegerRangeConstraint extends Constraint {

    private Integer minValue;

    public IntegerRangeConstraint(Integer minValue, Integer maxValue) {
        super(ConstraintName.RANGE_CHECK);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    private Integer maxValue;


    @Override
    public boolean validate(Object object) {

        if(object == null){
            return false;
        }
        Integer value = (Integer) (object);
        return value >= this.minValue && value <= this.maxValue;

    }
}
