package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.TrainingProgress;

import java.sql.Date;
import java.util.List;
import java.util.StringJoiner;

public class Training extends Entity {
    private String name;
    private Date startDate;
    private Date endDate;
    private TrainingProgress progress;
    private List<Topic> topics;
    private List<Task> tasks;

    public Training() {
    }

    public Training(int id, String name, Date startDate, Date endDate, TrainingProgress progress) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TrainingProgress getProgress() {
        return progress;
    }

    public void setProgress(TrainingProgress progress) {
        this.progress = progress;
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
        return (name == training.name || (name != null && name.equals(training.name))) &&
                (topics == training.topics || (topics != null && topics.equals(training.topics))) &&
                (tasks == training.tasks || (tasks != null && tasks.equals(training.tasks)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (name == null ? 0 : name.hashCode());
        result += seed * (topics == null ? 0 : topics.hashCode());
        result += seed * (tasks == null ? 0 : tasks.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Training.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("topics=" + topics)
                .add("tasks=" + tasks)
                .toString();
    }
}
