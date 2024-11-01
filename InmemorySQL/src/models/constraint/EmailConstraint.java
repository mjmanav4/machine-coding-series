package models.constraint;

public class EmailConstraint extends Constraint {


    private EmailConstraint emailConstraint = new EmailConstraint();

    public EmailConstraint getInstance() {
        return emailConstraint;
    }
    private EmailConstraint() {
        super(ConstraintName.RANGE_CHECK);
    }

    @Override
    public boolean validate(Object value) {
        if (value instanceof String) {
            String email = (String) value;

            return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"); // Basic email regex
        }
        return false;
    }
}