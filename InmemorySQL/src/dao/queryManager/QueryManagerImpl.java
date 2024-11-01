package dao.queryManager;

import dao.databasemanager.DatabaseManager;
import dao.databasemanager.DatabaseMangerImpl;
import dto.query.*;
import exception.database.record.RecordDoesNotExist;
import exception.database.record.RecordInvalidException;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dao.DaoUtils.applyOperator;
import static dao.DaoUtils.validateRow;

public class QueryManagerImpl implements QueryManager {

    private DatabaseManager databaseManager;
    private static QueryManager queryManagerImpl = new QueryManagerImpl();

    public static QueryManager getInstance() {
        return queryManagerImpl;
    }

    private QueryManagerImpl() {
        this.databaseManager = DatabaseMangerImpl.getInstance();
    }

    @Override
    public Integer insert(InsertQueryDTO insertRecord) {
        System.out.println("Attempting to insert record into table '" + insertRecord.getTableName() + "' in database '" + insertRecord.getDatabaseName() + "'...");
        Table table = databaseManager.getTableByName(insertRecord.getDatabaseName(), insertRecord.getTableName());
        Schema schema = table.getSchema();

        if (!validateRow(schema, insertRecord.getRecord())) {
            throw new RecordInvalidException("Invalid record violating constraint");
        }

        Integer nextId = table.getTableData().incrementId();
        insertRecord.getRecord().setRowId(nextId);
        table.getTableData().getRows().put(nextId, insertRecord.getRecord());

        System.out.println("Record inserted successfully with ID: " + nextId);
        return nextId;
    }

    @Override
    public void delete(DeleteQueryDTO deleteQueryDTO) {
        System.out.println("Attempting to delete record with ID " + deleteQueryDTO.getRowId() + " from table '" + deleteQueryDTO.getTableName() + "' in database '" + deleteQueryDTO.getDatabaseName() + "'...");
        Table table = databaseManager.getTableByName(deleteQueryDTO.getDatabaseName(), deleteQueryDTO.getTableName());

        if (!table.getTableData().getRows().containsKey(deleteQueryDTO.getRowId())) {
            System.out.println("Record doesn't exist with ID: " + deleteQueryDTO.getRowId());
            throw new RecordDoesNotExist("ID doesn't exist");
        }

        table.getTableData().getRows().remove(deleteQueryDTO.getRowId());
        System.out.println("Record with ID " + deleteQueryDTO.getRowId() + " deleted successfully.");
    }

    @Override
    public Row get(GetQueryDTO getQueryDTO) {
        System.out.println("Fetching record with ID " + getQueryDTO.getRowId() + " from table '" + getQueryDTO.getTableName() + "' in database '" + getQueryDTO.getDatabaseName() + "'...");
        Table table = databaseManager.getTableByName(getQueryDTO.getDatabaseName(), getQueryDTO.getTableName());

        if (!table.getTableData().getRows().containsKey(getQueryDTO.getRowId())) {
            System.out.println("Record doesn't exist with ID: " + getQueryDTO.getRowId());
            throw new RecordDoesNotExist("ID doesn't exist");
        }

        Row row = table.getTableData().getRows().get(getQueryDTO.getRowId());
        System.out.println("Record fetched successfully with ID: " + getQueryDTO.getRowId());
        return row;
    }

    @Override
    public Row update(UpdateQueryDTO updateQueryDTO) {
        System.out.println("Attempting to update record with ID " + updateQueryDTO.getRow().getRowId() + " in table '" + updateQueryDTO.getTableName() + "' in database '" + updateQueryDTO.getDatabaseName() + "'...");
        Table table = databaseManager.getTableByName(updateQueryDTO.getDatabaseName(), updateQueryDTO.getTableName());
        Schema schema = table.getSchema();

        if (!validateRow(schema, updateQueryDTO.getRow())) {
            throw new RecordInvalidException("Invalid record to update");
        }

        if (!table.getTableData().getRows().containsKey(updateQueryDTO.getRow().getRowId())) {
            System.out.println("Record doesn't exist with ID: " + updateQueryDTO.getRow().getRowId());
            throw new RecordDoesNotExist("ID doesn't exist");
        }

        Row currentRow = table.getTableData().getRows().get(updateQueryDTO.getRow().getRowId());
        for (Map.Entry<String, Object> updatedCols : updateQueryDTO.getRow().getValues().entrySet()) {
            currentRow.getValues().put(updatedCols.getKey(), updatedCols.getValue()); // Update or add the new value
        }

        System.out.println("Record with ID " + updateQueryDTO.getRow().getRowId() + " updated successfully.");
        return currentRow;
    }

    @Override
    public List<Row> printPaginated(PaginatedQueryDTO paginatedQueryDTO) {
        System.out.println("Fetching paginated records from table '" + paginatedQueryDTO.getTableName() + "' in database '" + paginatedQueryDTO.getDatabaseName() + "' starting from ID " + paginatedQueryDTO.getRowId() + "...");
        Table table = databaseManager.getTableByName(paginatedQueryDTO.getDatabaseName(), paginatedQueryDTO.getTableName());

        // Retrieve the rows as a Map
        Map<Integer, Row> rows = table.getTableData().getRows();
        int currRowId = paginatedQueryDTO.getRowId();
        int pageSize = paginatedQueryDTO.getPageSize();

        // Check if the specified starting row ID exists
        if (!rows.containsKey(currRowId)) {
            System.out.println("Record doesn't exist with ID: " + currRowId);
            throw new RecordDoesNotExist("ID doesn't exist");
        }

        List<Row> rowList = new ArrayList<>();

        // Collect rows up to the specified page size
        for (int i = 0; i < pageSize; i++) {
            Row currentRow = rows.get(currRowId + i); // Get the row for the current index
            if (currentRow == null) {
                // Stop if there are no more entries
                System.out.println("No more entries present after ID " + currRowId);
                break;
            }
            rowList.add(currentRow); // Add the current row to the list
        }

        System.out.println("Paginated records fetched successfully, number of records retrieved: " + rowList.size());
        return rowList; // Return the collected rows
    }

    @Override
    public List<Row> filterAndGet(FilterQueryDTO filterQueryDTO) {
        System.out.println("Filtering records from table '" + filterQueryDTO.getTableName() + "' in database '" + filterQueryDTO.getDatabaseName() + "'...");
        Table table = databaseManager.getTableByName(filterQueryDTO.getDatabaseName(), filterQueryDTO.getTableName());
        TableData tableData = table.getTableData();

        if (tableData == null) {
            System.out.println("No data available in table '" + filterQueryDTO.getTableName() + "'");
            return List.of();
        }

        List<Row> filteredRowList = new ArrayList<>();
        for (Map.Entry<Integer, Row> entry : tableData.getRows().entrySet()) {
            Row currentRecord = entry.getValue();
            if (applyOperator(currentRecord, filterQueryDTO.getColOperatorMap())) {
                filteredRowList.add(currentRecord);
            }
        }

        System.out.println("Filtering completed, number of records matching the criteria: " + filteredRowList.size());
        return filteredRowList;
    }
}
