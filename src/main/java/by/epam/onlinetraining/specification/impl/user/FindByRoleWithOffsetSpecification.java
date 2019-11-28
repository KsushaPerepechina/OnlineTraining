package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByRoleWithOffsetSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String role;
    private int limit;
    private int offset;

    public FindByRoleWithOffsetSpecification(UserRole role, int limit, int offset) {
        this.role = role.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSql() {
        return "WHERE role = ? AND users.activity = 'on' LIMIT ? OFFSET ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(role, limit, offset);
    }
}
