package by.epam.onlinetraining.specification.impl.record;

import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByTrainingIdAndStatusSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private int trainingId;
    private String status;

    public FindByTrainingIdAndStatusSpecification(int trainingId, StudentStatus status) {
        this.trainingId = trainingId;
        this.status = status.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
    }

    @Override
    public String toSql() {
        return "WHERE records.trainingId = ? AND records.status = ? AND records.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(trainingId, status);
    }
}
