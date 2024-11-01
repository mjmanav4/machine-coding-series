import dao.databasemanager.DatabaseManager;
import dao.databasemanager.DatabaseMangerImpl;
import dao.queryManager.QueryManager;
import dao.queryManager.QueryManagerImpl;
import dto.query.ColFilterOperation;
import dto.query.FilterQueryDTO;
import dto.query.InsertQueryDTO;
import dto.query.PaginatedQueryDTO;
import exception.database.record.RecordInvalidException;
import models.DataType;
import models.Database;
import models.Row;
import models.Schema;
import models.columns.Column;
import models.constraint.ConstraintName;
import models.constraint.integer.IntegerConstraint;
import models.constraint.integer.IntegerRangeConstraint;
import models.constraint.string.StringConstraint;
import models.constraint.string.StringLengthConstraint;
import operator.OperatorName;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = DatabaseMangerImpl.getInstance();
        String databaseName = "companyDB";

        // Create database and schema
        createDatabase(databaseManager, databaseName);
        Schema employeesSchema = createSchema();

        // Create table
        createTable(databaseManager, databaseName, employeesSchema);

        // Get query manager instance
        QueryManager queryManager = QueryManagerImpl.getInstance();

        // Insert records
        insertValidRecords(queryManager, databaseName, "employees");

        // Attempt to insert invalid records
        insertInvalidRecord(queryManager, databaseName, "employees");

        // Print all records
        printAllRecords(queryManager, databaseName, "employees");

        // Filter records for Engineering department
        filterEmployeesByDepartment(queryManager, databaseName, "employees", "Engineering");
    }

    private static Database createDatabase(DatabaseManager databaseManager, String databaseName) {
        databaseManager.create(databaseName);
        return databaseManager.get(databaseName);
    }

    private static Schema createSchema() {
        Column name = new Column(DataType.STRING, "name");
        name.addConstraint(ConstraintName.TYPE_CHECK, StringConstraint.getInstance());
        name.addConstraint(ConstraintName.NOT_NULLABLE_CHECK, StringConstraint.getInstance());
        name.addConstraint(ConstraintName.RANGE_CHECK, new StringLengthConstraint(2, 100));

        Column age = new Column(DataType.INTEGER, "age");
        age.addConstraint(ConstraintName.TYPE_CHECK, IntegerConstraint.getInstance());
        age.addConstraint(ConstraintName.RANGE_CHECK, new IntegerRangeConstraint(18, 60));

        Column department = new Column(DataType.STRING, "department");
        department.addConstraint(ConstraintName.TYPE_CHECK, StringConstraint.getInstance());
        department.addConstraint(ConstraintName.NOT_NULLABLE_CHECK, StringConstraint.getInstance());
        department.addConstraint(ConstraintName.RANGE_CHECK, new StringLengthConstraint(2, 100));

        HashMap<String, Column> schemaColMap = new HashMap<>();
        schemaColMap.put(name.getName(), name);
        schemaColMap.put(age.getName(), age);
        schemaColMap.put(department.getName(), department);

        return new Schema(schemaColMap);
    }

    private static void createTable(DatabaseManager databaseManager, String databaseName, Schema employeesSchema) {
        databaseManager.createTable(databaseName, employeesSchema, "employees");
    }

    private static void insertValidRecords(QueryManager queryManager, String databaseName, String tableName) {
        try {
            queryManager.insert(new InsertQueryDTO(databaseName, tableName, new Row(new HashMap<String, Object>() {{
                put("name", "Alice");
                put("age", 30);
                put("department", "Engineering");
            }})));

            queryManager.insert(new InsertQueryDTO(databaseName, tableName, new Row(new HashMap<String, Object>() {{
                put("name", "Bob");
                put("age", 45);
                put("department", "Marketing");
            }})));
            System.out.println("Inserted all data successfully");
        } catch (RecordInvalidException e) {
            System.out.println("Failed to insert record batch: " + e.getMessage());
        }
    }

    private static void insertInvalidRecord(QueryManager queryManager, String databaseName, String tableName) {
        try {
            queryManager.insert(new InsertQueryDTO(databaseName, tableName, new Row(new HashMap<String, Object>() {{
                put("name", "Charlie");
                put("age", 17); // Invalid: age < 18
                put("department", "Sales");
            }})));
        } catch (RecordInvalidException e) {
            System.out.println("Failed to insert record: " + e.getMessage());
        }
    }

    private static void printAllRecords(QueryManager queryManager, String databaseName, String tableName) {
        List<Row> allRows = queryManager.printPaginated(new PaginatedQueryDTO(databaseName, tableName, 1, 10));
        for (Row row : allRows) {
            System.out.println(row.getValues());
        }
    }

    private static void filterEmployeesByDepartment(QueryManager queryManager, String databaseName, String tableName, String department) {
        FilterQueryDTO filterQuery = new FilterQueryDTO(databaseName, tableName, new HashMap<>() {{
            put("department", new ColFilterOperation(department, OperatorName.EQUAL));
        }});
        List<Row> engineeringEmployees = queryManager.filterAndGet(filterQuery);
        System.out.println("Employees in the '" + department + "' Department:");
        for (Row row : engineeringEmployees) {
            System.out.println(row.getValues());
        }
    }
}
