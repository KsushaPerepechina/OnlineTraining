package by.epam.onlinetraining.specification.impl.record;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByTrainingIdSpecification implements SqlSpecification {
    private int trainingId;
    private String tableName;

    public FindByTrainingIdSpecification(int trainingId, String tableName) {
        this.trainingId = trainingId;
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName +".training_id = ? AND " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(trainingId);
    }
}
