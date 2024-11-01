package dao.databasemanager;

import models.columns.Column;
import models.Database;
import models.Schema;
import models.Table;

import java.util.HashMap;
import java.util.Set;

public interface DatabaseManager {

     void create(String databaseName);

    Database get(String databaseName);

    Set<String> getListOfTables(String databaseName);

    void createTable(String databaseName, Schema schema, String tableName);

    void deleteTableByName(String databaseName, String tableName);

    Table getTableByName(String databaseName, String tableName);

    void addColInTable(String databaseName, HashMap<String, Column> columnHashMap, String tableName);

}
