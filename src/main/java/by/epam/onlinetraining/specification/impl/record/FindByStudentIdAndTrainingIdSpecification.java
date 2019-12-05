package by.epam.onlinetraining.specification.impl.record;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByStudentIdAndTrainingIdSpecification implements SqlSpecification {
    private int studentId;
    private int trainingId;

    public FindByStudentIdAndTrainingIdSpecification(int studentId, int trainingId) {
        this.studentId = studentId;
        this.trainingId = trainingId;
    }

    @Override
    public String toSql() {
        return "WHERE records.student_id = ? AND records.training_id = ? AND records.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(studentId, trainingId);
    }
}
