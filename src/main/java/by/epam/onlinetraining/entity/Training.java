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
    private User mentor;
    private List<StudentRecord> students;

    public Training() {
    }

    public Training(Integer id, String name, Date startDate, Date endDate,
                    TrainingProgress progress, User mentor, List<StudentRecord> students) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.students = students;
        this.mentor = mentor;
    }

    public Training(Integer id, String name, Date startDate, Date endDate,
                    TrainingProgress progress, User mentor) {//TODO remove
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.mentor = mentor;
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

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    public List<StudentRecord> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRecord> students) {
        this.students = students;
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
