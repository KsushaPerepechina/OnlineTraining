package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.ConsultationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Consultation extends Entity {
    private User student;
    private Training training;
    private LocalDate date;
    private BigDecimal cost;
    private ConsultationStatus status;
    private int performance;
    private int quality;
    private boolean payed;
    private List<ConsultationAssignment> assignments;

    public Consultation() {
    }

    public Consultation(int id) {
        super(id);
    }


    public Consultation(Integer id, User student, Training training, LocalDate date, BigDecimal cost,
                        ConsultationStatus status, int performance, int quality, boolean payed) {
        super(id);
        this.student = student;
        this.training = training;
        this.date = date;
        this.cost = cost;
        this.status = status;
        this.performance = performance;
        this.quality = quality;
        this.payed = payed;
    }

    public Consultation(User student, Training training) {
        this.student = student;
        this.training = training;
    }

    public Consultation(int id, LocalDate date, ConsultationStatus status, boolean payed) {
        super(id);
        this.date = date;
        this.status = status;
        this.payed = payed;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return cost;
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

    public List<ConsultationAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<ConsultationAssignment> assignments) {
        this.assignments = assignments;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
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
        return performance == that.performance &&
                quality == that.quality &&
                status == that.status &&
                payed == that.payed &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(student, that.student) &&
                Objects.equals(training, that.training) &&
                Objects.equals(date, that.date) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(assignments, that.assignments);
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (student == null ? 0 : student.hashCode());
        result += seed * (training == null ? 0 : training.hashCode());
        result += seed * (date == null ? 0 : date.hashCode());
        result += seed * (cost == null ? 0 : cost.hashCode());
        result += seed * (status == null ? 0 : status.hashCode());
        result += seed * performance;
        result += seed * quality;
        result += seed * Boolean.hashCode(payed);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Consultation.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("student=" + student)
                .add("training=" + training)
                .add("date=" + date)
                .add("cost=" + cost)
                .add("performance=" + performance)
                .add("quality=" + quality)
                .add("payed=" + payed)
                .add("assignments=" + assignments)
                .toString();
    }
}
