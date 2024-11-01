package dao;

import dto.query.ColFilterOperation;
import models.columns.Column;
import models.Row;
import models.Schema;
import models.constraint.Constraint;
import models.constraint.ConstraintName;
import operator.Operator;
import operator.OperatorFactory;
import operator.OperatorName;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DaoUtils {

    public static Comparator<ConstraintName> customComparator = Comparator.comparingInt(ConstraintName::getOrder);

    private static boolean validateColConstraints(Object value, Map<ConstraintName, Constraint> constraintMap) {
        if (constraintMap == null || constraintMap.isEmpty()) {
            return true;
        }
        for (Constraint constraint : constraintMap.values()) {
            if (!constraint.validate(value)) {
                return false;  // Return false if any constraint fails
            }
        }
        return true;  // Return true if all constraints pass
    }

    public static boolean validateRow(Schema schema, Row row) {
        for (Map.Entry<String, Column> entry : schema.getColumnHashMap().entrySet()) {
            String colName = entry.getKey();
            Column columnSchema = entry.getValue();

            // Check if the column allows null values
            if (!isNullable(columnSchema, row.getValues().get(colName))) {

                System.out.println("Validation failed: Column '" + colName + "' does not allow null values but received null.");

                return false;
            }

            // Validate the column's constraints if value is non-null
            Object colValue = row.getValues().get(colName);
            if (colValue != null && !validateColConstraints(colValue, columnSchema.getConstraints())) {
                System.out.println("Validation Error: Column '" + colName + "' with value '" + colValue + "' violates the constraint(s).");
                return false;
            }
        }
        return true;
    }

    private static boolean isNullable(Column column, Object colValue) {
        // If the column is missing in the row values and has NOT_NULLABLE_CHECK, it's invalid
        if (colValue == null && column.getConstraints().containsKey(ConstraintName.NOT_NULLABLE_CHECK)) {
            return false;
        }
        return true;
    }

    public static boolean applyOperator(Row row, HashMap<String, ColFilterOperation> colFilterOperationHashMap) {

        for (Map.Entry<String, ColFilterOperation> colFilter : colFilterOperationHashMap.entrySet()) {


            String colName = colFilter.getKey();

            Object desiredValue = colFilter.getValue().getDesiredValue();
            Object currValue = row.getValues().get(colName);

            OperatorName operatorName = colFilter.getValue().getOperatorName();

            Operator operator = OperatorFactory.getOperator(operatorName);

            if (!operator.apply(currValue, desiredValue)) {
                return false;
            }

        }
        return true;
    }
}
