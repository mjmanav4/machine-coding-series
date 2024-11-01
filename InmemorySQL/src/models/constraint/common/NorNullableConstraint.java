package models.constraint.common;

import models.constraint.Constraint;
import models.constraint.ConstraintName;

public class NorNullableConstraint extends Constraint {

	private static NorNullableConstraint notNullableConstraint = new NorNullableConstraint();

	public static  NorNullableConstraint getInstance() {
		return notNullableConstraint;
	}
	private NorNullableConstraint() {
		super(ConstraintName.NOT_NULLABLE_CHECK);
	}

	@Override
	public boolean validate(Object object) {
		if(object == null)
			return false;
		return true;
	}

}
