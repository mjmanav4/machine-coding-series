package models;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Row {

    private Integer rowId;
    private HashMap<String, Object> values;

    public Row(HashMap<String, Object> values) {

        this.values = values;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public HashMap<String, Object> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Object> values) {
        this.values = values;
    }

}
