package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.ConsultationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class Consultation extends Entity {
    private User student;
    private Training training;
    private LocalDate dateTime;
    private BigDecimal cost;
    private ConsultationStatus status;
    private int performance;
    private int quality;
    private List<Assignment> assignments;//TODO

    public Consultation() {
    }

    public Consultation(Integer id, User student, Training training, LocalDate dateTime, BigDecimal cost,
                        ConsultationStatus status, int performance, int quality) {
        super(id);
        this.student = student;
        this.training = training;
        this.dateTime = dateTime;
        this.cost = cost;
        this.status = status;
        this.performance = performance;
        this.quality = quality;
    }

    public Consultation(User student, Training training) {
        this.student = student;
        this.training = training;
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

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Consultation that = (Consultation) o;
        return performance == that.performance && quality == that.quality && status != null && status == that.status &&
                getId() != null && getId().equals(that.getId()) &&
                (student == that.student || (student != null && student.equals(that.student))) &&
                (training == that.training || (training != null && training.equals(that.training))) &&
                (dateTime == that.dateTime || (dateTime != null && dateTime.equals(that.dateTime))) &&
                (cost == that.cost || (cost != null && cost.equals(that.cost)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (student == null ? 0 : student.hashCode());
        result += seed * (training == null ? 0 : training.hashCode());
        result += seed * (dateTime == null ? 0 : dateTime.hashCode());
        result += seed * (cost == null ? 0 : cost.hashCode());
        result += seed * (status == null ? 0 : status.hashCode());
        result += seed * performance;
        result += seed * quality;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Consultation.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("student=" + student)
                .add("training=" + training)
                .add("dateTime=" + dateTime)
                .add("cost=" + cost)
                .add("performance=" + performance)
                .add("quality=" + quality)
                .add("assignments=" + assignments)
                .toString();
    }
}
