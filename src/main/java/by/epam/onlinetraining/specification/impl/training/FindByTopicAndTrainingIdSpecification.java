package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByTopicAndTrainingIdSpecification implements SqlSpecification {
    private int topicId;
    private int trainingId;

    public FindByTopicAndTrainingIdSpecification(int topicId, int trainingId) {
        this.topicId = topicId;
        this.trainingId = trainingId;
    }

    @Override
    public String toSql() {
        return "WHERE topic_id = ? AND training_id = ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(topicId, trainingId);
    }
}
