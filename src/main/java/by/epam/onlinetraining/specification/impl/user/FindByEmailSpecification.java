package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByEmailSpecification implements SqlSpecification {
    private String email;

    public FindByEmailSpecification(String email) {
        this.email = email;
    }

    @Override
    public String toSql() {
        return "WHERE email = ?";
    }

    public List<Object> getParameters() {
        return Collections.singletonList(email);
    }
}
