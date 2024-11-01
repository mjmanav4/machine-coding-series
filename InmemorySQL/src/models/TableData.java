package models;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TableData {
    public Integer getAutoId() {
        return autoId;
    }

    public synchronized   Integer incrementId() {
        return  ++this.autoId;
    }
    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public LinkedHashMap<Integer, Row> getRows() {
        return rows;
    }

    public void setRows(LinkedHashMap<Integer, Row> rows) {
        this.rows = rows;
    }

    private Integer autoId;
    private LinkedHashMap<Integer, Row> rows;


    public TableData(){
        this.autoId = 0;
        this.rows = new LinkedHashMap<>();
    }
}
