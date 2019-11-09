package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByRoleSpecification implements SqlSpecification {
    private UserRole role;

    public FindByRoleSpecification(UserRole role) {
        this.role = role;
    }

    @Override
    public String toSql() {
        return "WHERE role_id = (SELECT id FROM user_roles WHERE name = ?)";
    }

    public List<Object> getParameters() {
        return Collections.singletonList(role);
    }
}
