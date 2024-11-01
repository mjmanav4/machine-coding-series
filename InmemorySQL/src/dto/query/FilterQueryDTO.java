package dto.query;

import operator.Operator;
import operator.OperatorName;

import java.util.HashMap;

public class FilterQueryDTO extends BaseQuery {

    public FilterQueryDTO(String databaseName, String tableName,HashMap<String, ColFilterOperation> colOperatorMap ) {
        super(databaseName, tableName);
        this.colOperatorMap = colOperatorMap;
    }

    public HashMap<String, ColFilterOperation> getColOperatorMap() {
        return colOperatorMap;
    }

    public void setColOperatorMap(HashMap<String, ColFilterOperation> colOperatorMap) {
        this.colOperatorMap = colOperatorMap;
    }

    // colName and operatorName
    HashMap<String, ColFilterOperation> colOperatorMap;
}
