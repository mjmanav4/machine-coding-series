package dto.query;

public class PaginatedQueryDTO extends  BaseQuery {
    Integer rowId;
    Integer pageSize;

    public PaginatedQueryDTO(String databaseName, String tableName, Integer rowId, Integer pageSize) {
        super(databaseName, tableName);
        this.rowId = rowId;
        this.pageSize = pageSize;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
