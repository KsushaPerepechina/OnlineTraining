package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByStudentAndTrainingIdSpecification implements SqlSpecification {
    private int studentId;
    private int trainingId;

    public FindByStudentAndTrainingIdSpecification(int studentId, int trainingId) {
        this.studentId = studentId;
        this.trainingId = trainingId;
    }

    @Override
    public String toSql() {
        return "WHERE student_id = ? AND training_id = ?";
    }

    public List<Object> getParameters() {
        return Arrays.asList(studentId, trainingId);
    }
}
