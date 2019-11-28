package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByProgressSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String progress;

    public FindByProgressSpecification(TrainingProgress progress) {
        this.progress = progress.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
    }

    @Override
    public String toSql() {
        return "WHERE progress = ? AND t.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(progress);
    }
}
