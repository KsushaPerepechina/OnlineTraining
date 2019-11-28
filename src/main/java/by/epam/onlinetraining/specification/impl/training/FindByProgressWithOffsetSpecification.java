package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByProgressWithOffsetSpecification implements SqlSpecification {
    private TrainingProgress progress;
    private int limit;
    private int offset;

    public FindByProgressWithOffsetSpecification(TrainingProgress progress, int limit, int offset) {
        this.progress = progress;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSql() {
        return "WHERE progress = ? AND t.activity = 'on' LIMIT ? OFFSET ?";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(progress, limit, offset);
    }
}