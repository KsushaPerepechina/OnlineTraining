package by.epam.onlinetraining.specification;

import java.util.List;

/**
 * Designed to determine the behavior of the implementing class in the form of specification to making query.
 */
public interface SqlSpecification {
    /**
     * Add the second part of sql-query for the main part.
     *
     * @return an {@link java.lang.String} objects with specified sql-query.
     */
    String toSql();

    /**
     * Add the second part of sql-query for the main part.
     *
     * @return an {@link java.util.List} implementation with {@link java.lang.Object} parameters for sql-query.
     */
    List<Object> getParameters();
}
