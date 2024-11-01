package dto.query;

import models.Row;

import java.util.HashMap;

public class UpdateQueryDTO extends BaseQuery {

    public UpdateQueryDTO(String databaseName, String tableName, Row row) {
        super(databaseName, tableName);
        this.row = row;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    Row row;

}
