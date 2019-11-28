package by.epam.onlinetraining.specification.impl;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindAllSpecification implements SqlSpecification {
    private String tableName;

    public FindAllSpecification(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}
