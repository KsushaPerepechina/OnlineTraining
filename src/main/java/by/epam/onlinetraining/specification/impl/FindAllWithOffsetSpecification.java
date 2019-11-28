package by.epam.onlinetraining.specification.impl;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindAllWithOffsetSpecification implements SqlSpecification {
    private String tableName;
    private Integer limit;
    private Integer offset;

    public FindAllWithOffsetSpecification(String tableName, Integer limit, Integer offset) {
        this.tableName = tableName;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName + ".activity = 'on' LIMIT ? OFFSET ?";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(limit, offset);
    }
}
