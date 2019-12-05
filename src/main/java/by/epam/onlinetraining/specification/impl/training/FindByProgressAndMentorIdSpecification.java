package by.epam.onlinetraining.specification.impl.training;

import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class FindByProgressAndMentorIdSpecification implements SqlSpecification {
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private String progress;
    private int mentorId;

    public FindByProgressAndMentorIdSpecification(TrainingProgress progress, int mentorId) {
        this.progress = progress.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        this.mentorId = mentorId;
    }

    @Override
    public String toSql() {
        return "WHERE trainings.progress = ? AND trainings.mentor_id = ? AND trainings.activity = 'on'";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(progress, mentorId);
    }
}
