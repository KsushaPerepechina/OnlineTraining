package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByTaskAndTrainingIdSpecification implements SqlSpecification {
    private int taskId;
    private int trainingId;

    public FindByTaskAndTrainingIdSpecification(int taskId, int trainingId) {
        this.taskId = taskId;
        this.trainingId = trainingId;
    }

    @Override
    public String toSql() {
        return "WHERE task_id = ? AND training_id = ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(taskId, trainingId);
    }
}
