package models;

import java.util.UUID;

public class Table {

    public Table(String tableName, Schema schema) {
        this.tableId = UUID.randomUUID().toString();
        this.tableName = tableName;
        this.schema = schema;
        this.tableData = new TableData();
    }

    String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public TableData getTableData() {
        return tableData;
    }

    public void setTableData(TableData tableData) {
        this.tableData = tableData;
    }

    private String tableName;
    private Schema schema;
    private TableData tableData;
}