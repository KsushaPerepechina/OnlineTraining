package by.epam.onlinetraining.specification.impl.record;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByStudentIdSpecification implements SqlSpecification {
    private int studentId;
    private String tableName;

    public FindByStudentIdSpecification(int studentId, String tableName) {
        this.studentId = studentId;
        this.tableName = tableName;
    }

    @Override
    public String toSql() {
        return "WHERE " + tableName +".student_id = ? AND " + tableName + ".activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(studentId);
    }
}
