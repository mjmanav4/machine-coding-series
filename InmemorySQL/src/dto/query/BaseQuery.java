package dto.query;

public abstract class BaseQuery {
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    String databaseName;

    public BaseQuery(String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    String tableName;
}
