package by.epam.onlinetraining.specification.impl.consultation;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByTopicAndConsultationIdSpecification implements SqlSpecification {
    private int topicId;
    private int consultationId;

    public FindByTopicAndConsultationIdSpecification(int topicId, int consultationId) {
        this.topicId = topicId;
        this.consultationId = consultationId;
    }

    @Override
    public String toSql() {
        return "WHERE topic_id = ? AND consultation_id = ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(topicId, consultationId);
    }
}
