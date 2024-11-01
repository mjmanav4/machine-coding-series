package dto.query;

import models.Row;

public class InsertQueryDTO extends BaseQuery {
    public InsertQueryDTO(String databaseName, String tableName, Row record) {
        super(databaseName, tableName);
        this.record = record;
    }

    public Row getRecord() {
        return record;
    }

    public void setRecord(Row record) {
        this.record = record;
    }

    Row record;
}
