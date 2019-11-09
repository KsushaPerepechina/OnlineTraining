package by.epam.onlinetraining.specification.impl;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByIdSpecification implements SqlSpecification {
    private int id;

    public FindByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return "WHERE id = ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}
