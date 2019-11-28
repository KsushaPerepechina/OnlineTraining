package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByRoleSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String role;

    public FindByRoleSpecification(UserRole role) {
        this.role = role.toString()
                .toLowerCase()
                .replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
    }

    @Override
    public String toSql() {
        return "WHERE role = ? AND users.activity = 'on'";
    }

    public List<Object> getParameters() {
        return Collections.singletonList(role);
    }
}
