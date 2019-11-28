package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.StudentStatus;

import java.util.StringJoiner;

public class StudentRecord extends Entity {//TODO
    private User student;
    private StudentStatus status;
    private Integer mark;

    public StudentRecord(User student, StudentStatus status, int mark) {
        this.student = student;
        this.status = status;
        this.mark = mark;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
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
    public String toString() {
        return new StringJoiner(", ", StudentRecord.class.getSimpleName() + "[", "]")
                .add("student=" + student)
                .add("status=" + status)
                .add("mark=" + mark)
                .toString();
    }
}
