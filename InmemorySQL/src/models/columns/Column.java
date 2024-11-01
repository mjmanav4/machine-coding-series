package models.columns;

import dao.DaoUtils;
import models.DataType;
import models.constraint.Constraint;
import models.constraint.ConstraintName;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public  class Column {


    DataType type;
    private String name;
    private Map<ConstraintName, Constraint> constraintNameConstraintMap;


    public Column(DataType type, String name) {
        // Use a comparator based on the custom order defined in the enum

        this.type = type;
        this.name = name;
        // Initialize TreeMap with the custom comparator
        constraintNameConstraintMap = new TreeMap<>(DaoUtils.customComparator);
    }


    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addConstraint(ConstraintName constraintName, Constraint constraint) {
        constraintNameConstraintMap.put(constraintName, constraint);
    }

    public Map<ConstraintName, Constraint> getConstraints() {
        return constraintNameConstraintMap;
    }
}
