package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByProgressSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String progress;
    private String tableName;

    public FindByProgressSpecification(TrainingProgress progress, String tableName) {
        this.progress = progress.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE progress = ? AND " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(progress);
    }
}
