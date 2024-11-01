package models.constraint;

public class PhoneNumberConstraint extends Constraint {
    public PhoneNumberConstraint() {
        super(ConstraintName.REGEX_CHECK);
    }

    @Override
    public boolean validate(Object value) {
        if (value instanceof String) {
            String phone = (String) value;
            return phone.matches("^[0-9]{10}$"); // Validates a 10-digit phone number
        }
        return false;
    }
}

