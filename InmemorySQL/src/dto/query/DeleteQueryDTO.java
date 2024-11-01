package dto.query;

public class DeleteQueryDTO extends BaseQuery {
    public DeleteQueryDTO(String databaseName, String tableName, Integer rowId) {
        super(databaseName, tableName);
        this.rowId = rowId;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    Integer rowId;
}
