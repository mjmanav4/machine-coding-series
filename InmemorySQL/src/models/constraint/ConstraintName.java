package models.constraint;

public enum ConstraintName {

    NOT_NULLABLE_CHECK(1),
    TYPE_CHECK(2),
 
    RANGE_CHECK(3),

    REGEX_CHECK(4);

    private final int order;

    ConstraintName(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}