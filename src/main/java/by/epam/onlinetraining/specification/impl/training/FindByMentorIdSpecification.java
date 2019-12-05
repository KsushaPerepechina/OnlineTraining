package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByMentorIdSpecification implements SqlSpecification {
    private int mentorId;
    private String tableName;

    public FindByMentorIdSpecification(int mentorId, String tableName) {
        this.mentorId = mentorId;
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName +".mentor_id = ? AND " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(mentorId);
    }
}
