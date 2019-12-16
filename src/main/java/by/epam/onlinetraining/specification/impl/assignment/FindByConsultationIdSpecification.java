package by.epam.onlinetraining.specification.impl.assignment;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByConsultationIdSpecification implements SqlSpecification {
    private int consultationId;

    public FindByConsultationIdSpecification(int consultationId) {
        this.consultationId = consultationId;
    }

    @Override
    public String toSql() {
        return "WHERE consultation_id = ? AND consultation_assignments.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(consultationId);
    }
}
