package by.epam.onlinetraining.specification;

import java.util.List;

public interface SqlSpecification {
    String toSql();
    List<Object> getParameters();
}
