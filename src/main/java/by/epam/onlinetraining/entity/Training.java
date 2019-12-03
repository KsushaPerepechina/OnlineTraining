package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.TrainingProgress;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Training extends Entity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TrainingProgress progress;
    private User mentor;

    public Training() {
    }

    public Training(Integer id, String name, LocalDate startDate, LocalDate endDate,
                    TrainingProgress progress, User mentor) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.mentor = mentor;
    }

    public Training(Integer id, User mentor) {
        super(id);
        this.mentor = mentor;
    }

    public Training(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TrainingProgress getProgress() {
        return progress;
    }

    public void setProgress(TrainingProgress progress) {
        this.progress = progress;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Training training = (Training) o;
        return progress == training.progress &&
                getId() != null && getId().equals(training.getId()) &&
                (name == training.name || (name != null && name.equals(training.name))) &&
                (startDate == training.startDate || (startDate != null && startDate.equals(training.startDate))) &&
                (endDate == training.endDate || (endDate != null && endDate.equals(training.endDate))) &&
                (mentor == training.mentor || (mentor != null && mentor.equals(training.mentor)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (name == null ? 0 : name.hashCode());
        result += seed * (startDate == null ? 0 : startDate.hashCode());
        result += seed * (endDate == null ? 0 : endDate.hashCode());
        result += seed * progress.hashCode();
        result += seed * (mentor == null ? 0 : mentor.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Training.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("progress=" + progress)
                .add("mentor=" + mentor)
                .toString();
    }
}
