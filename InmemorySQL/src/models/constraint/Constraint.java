package models.constraint;

public abstract class Constraint {


	public Constraint(ConstraintName constraintName) {
		this.constraintName = constraintName;
	}


	public ConstraintName getConstraintName() {
		return constraintName;
	}

	private ConstraintName constraintName;


    public abstract boolean validate(Object object);
}
