package by.epam.onlinetraining.specification.impl.assignment;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByAssignmentIdAndConsultationIdSpecification implements SqlSpecification {
    private int assignmentId;
    private int consultationId;

    public FindByAssignmentIdAndConsultationIdSpecification(int assignmentId, int consultationId) {
        this.assignmentId = assignmentId;
        this.consultationId = consultationId;
    }

    @Override
    public String toSql() {
        return "WHERE assignment_id = ? AND consultation_id = ? AND consultation_assignments.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(assignmentId, consultationId);
    }
}
