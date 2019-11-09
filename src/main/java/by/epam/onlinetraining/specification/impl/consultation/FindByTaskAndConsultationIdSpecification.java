package by.epam.onlinetraining.specification.impl.consultation;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByTaskAndConsultationIdSpecification implements SqlSpecification {
    private int taskId;
    private int consultationId;

    public FindByTaskAndConsultationIdSpecification(int taskId, int consultationId) {
        this.taskId = taskId;
        this.consultationId = consultationId;
    }

    @Override
    public String toSql() {
        return "WHERE task_id = ? AND consultation_id = ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(taskId, consultationId);
    }
}
