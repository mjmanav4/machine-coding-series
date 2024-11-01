package models;

import models.columns.Column;

import java.util.HashMap;
import java.util.UUID;

public class Schema {

    private String schemaId;
    private HashMap<String, Column> columnHashMap;

    public Schema(HashMap<String, Column> columns) {
        this.schemaId = UUID.randomUUID().toString();
        this.columnHashMap = columns;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public HashMap<String, Column> getColumnHashMap() {
        return columnHashMap;
    }

    public void setColumnHashMap(HashMap<String, Column> columnHashMap) {
        this.columnHashMap = columnHashMap;
    }



}
