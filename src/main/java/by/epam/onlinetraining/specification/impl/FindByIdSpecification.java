package by.epam.onlinetraining.specification.impl;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByIdSpecification implements SqlSpecification {
    private int id;
    private String tableName;

    public FindByIdSpecification(int id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName + ".id = ? AND " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}
