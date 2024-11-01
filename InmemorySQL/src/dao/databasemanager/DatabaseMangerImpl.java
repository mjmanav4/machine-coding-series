package dao.databasemanager;

import exception.database.database.DatabaseAlreadyExistsException;
import exception.database.database.DatabaseNotPresent;
import exception.database.table.TableAlreadyExist;
import exception.database.table.TableDoesNotExist;
import models.columns.Column;
import models.Database;
import models.Schema;
import models.Table;

import java.util.*;

public class DatabaseMangerImpl implements DatabaseManager {

    private static DatabaseManager databaseMangerImpl = new DatabaseMangerImpl();

    public static DatabaseManager getInstance() {
        return databaseMangerImpl;
    }

    private DatabaseMangerImpl() {
    }

    // name is the key here
    HashMap<String, Database> databaseHashMap = new HashMap<>();

    @Override
    public void create(String databaseName) {
        System.out.println("Attempting to create database: " + databaseName);
        if (databaseHashMap.containsKey(databaseName)) {
            throw new DatabaseAlreadyExistsException("A database with the name '" + databaseName + "' already exists.");
        }
        databaseHashMap.putIfAbsent(databaseName, new Database(UUID.randomUUID().toString(), databaseName, new HashMap<>()));
        System.out.println("Database created successfully: " + databaseName);
    }

    @Override
    public Database get(String databaseName) {
        System.out.println("Fetching database: " + databaseName);
        Database db = databaseHashMap.getOrDefault(databaseName, null);
        if (db != null) {
            System.out.println("Database retrieved successfully: " + databaseName);
        } else {
            System.out.println("Database not found: " + databaseName);
        }
        return db;
    }

    @Override
    public Set<String> getListOfTables(String databaseName) {
        System.out.println("Fetching list of tables from database: " + databaseName);
        Set<String> tables = databaseHashMap.getOrDefault(databaseName, null).getTableMap().keySet();
        System.out.println("Retrieved list of tables for database: " + databaseName);
        return tables;
    }

    @Override
    public void createTable(String databaseName, Schema schema, String tableName) {
        System.out.println("Attempting to create table '" + tableName + "' in database '" + databaseName + "'...");

        Database database = databaseHashMap.get(databaseName);
        if (database == null) {
            throw new DatabaseNotPresent(databaseName);
        }
        if (database.getTableMap().containsKey(tableName)) {
            throw new TableAlreadyExist(tableName);
        }

        Table table = new Table(tableName, schema);
        database.getTableMap().put(tableName, table);

        System.out.println("Table '" + tableName + "' created successfully in database '" + databaseName + "'.");
    }

    public void deleteTableByName(String databaseName, String tableName) {
        System.out.println("Attempting to delete table '" + tableName + "' from database '" + databaseName + "'...");

        Database database = databaseHashMap.get(databaseName);
        if (database == null) {
            throw new DatabaseNotPresent(databaseName);
        }
        if (!database.getTableMap().containsKey(tableName)) {
            throw new TableDoesNotExist(tableName);
        }

        database.getTableMap().remove(tableName);
        System.out.println("Table '" + tableName + "' deleted successfully from database '" + databaseName + "'.");
    }

    @Override
    public Table getTableByName(String databaseName, String tableName) {
        System.out.println("Fetching table '" + tableName + "' from database '" + databaseName + "'...");

        Database database = databaseHashMap.get(databaseName);
        if (database == null) {
            throw new DatabaseNotPresent(databaseName);
        }
        if (!database.getTableMap().containsKey(tableName)) {
            throw new TableDoesNotExist(tableName);
        }

        Table table = database.getTableMap().get(tableName);
        System.out.println("Table '" + tableName + "' retrieved successfully from database '" + databaseName + "'.");
        return table;
    }

    @Override
    public void addColInTable(String databaseName, HashMap<String, Column> updateColMap, String tableName) {
        System.out.println("Attempting to add columns in table '" + tableName + "' in database '" + databaseName + "'...");

        Database database = databaseHashMap.get(databaseName);
        if (database == null) {
            throw new DatabaseNotPresent(databaseName);
        }
        if (!database.getTableMap().containsKey(tableName)) {
            throw new TableDoesNotExist(tableName);
        }

        Table table = database.getTableMap().get(tableName);
        // First, check if there are any duplicate keys
        boolean hasDuplicate = false;
        for (String key : updateColMap.keySet()) {
            if (table.getSchema().getColumnHashMap().containsKey(key)) {
                System.out.println("Duplicate column name found: " + key);
                hasDuplicate = true;
                break;
            }
        }

        if (hasDuplicate) {
            System.out.println("Column addition aborted due to duplicate column names.");
            return;
        }

        table.getSchema().getColumnHashMap().putAll(updateColMap);
        System.out.println("All columns added successfully in table '" + tableName + "'.");
    }
}
