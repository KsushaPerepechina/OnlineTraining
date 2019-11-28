package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByRoleAndBlockingStatusSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String role;
    private String blockingStatus;

    public FindByRoleAndBlockingStatusSpecification(UserRole role, BlockingStatus blockingStatus) {
        this.role = role.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        this.blockingStatus = blockingStatus.toString().toLowerCase();
    }

    @Override
    public String toSql() {
        return "WHERE role = ? AND blocking_status = ? AND users.activity = 'on'";
    }

    public List<Object> getParameters() {
        return Arrays.asList(role, blockingStatus);
    }
}