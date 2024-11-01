package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database {


    String databaseId;
    String dataBaseName;

    // tableName, tableId // it's the foreign  key
    HashMap<String, Table> tableHashMap;


    public Database(String databaseId, String dataBaseName, HashMap<String, Table> tableHashMap) {
        this.databaseId = databaseId;
        this.dataBaseName = dataBaseName;
        this.tableHashMap = tableHashMap;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public Map<String, Table> getTableMap() {
        return tableHashMap;
    }

    public void setTableMap(HashMap<String, Table> tableMap) {
        this.tableHashMap = tableMap;
    }


}
