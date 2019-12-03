package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.StudentStatus;

import java.util.StringJoiner;

public class Record extends Entity {
    private User student;
    private Training training;
    private StudentStatus status;
    private Integer mark;

    public Record(Integer id, User student, Training training, StudentStatus status, Integer mark) {
        super(id);
        this.student = student;
        this.training = training;
        this.status = status;
        this.mark = mark;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record that = (Record) o;
        return status != null && status == that.status && getId() != null && getId().equals(that.getId()) &&
                (training == that.training || (training != null && training.equals(that.training))) &&
                (student == that.student || (student != null && student.equals(that.student))) &&
                (mark == that.mark || (mark != null && mark.equals(that.mark)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (student == null ? 0 : student.hashCode());
        result += seed * (training == null ? 0 : training.hashCode());
        result += seed * (status == null ? 0 : status.hashCode());
        result += seed * (mark == null ? 0 : mark.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Record.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("student=" + student)
                .add("training=" + training)
                .add("status=" + status)
                .add("mark=" + mark)
                .toString();
    }
}
