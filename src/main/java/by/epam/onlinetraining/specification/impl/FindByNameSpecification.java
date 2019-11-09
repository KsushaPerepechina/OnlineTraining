package by.epam.onlinetraining.specification.impl;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByNameSpecification implements SqlSpecification {
    private String name;

    public FindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public String toSql() {
        return "WHERE name = ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(name);
    }
}
