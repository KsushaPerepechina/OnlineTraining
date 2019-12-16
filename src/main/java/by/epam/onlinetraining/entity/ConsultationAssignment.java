package by.epam.onlinetraining.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class ConsultationAssignment extends Entity {
    private Consultation consultation;
    private Assignment assignment;

    public ConsultationAssignment() {
    }

    public ConsultationAssignment(Consultation consultation, Assignment assignment) {
        this.consultation = consultation;
        this.assignment = assignment;
    }

    public ConsultationAssignment(Integer id, Consultation consultation, Assignment assignment) {
        super(id);
        this.consultation = consultation;
        this.assignment = assignment;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsultationAssignment that = (ConsultationAssignment) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(consultation, that.consultation) &&
                Objects.equals(assignment, that.assignment);
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (consultation == null ? 0 : consultation.hashCode());
        result += seed * (assignment == null ? 0 : assignment.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConsultationAssignment.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("consultation=" + consultation)
                .add("assignment=" + assignment)
                .toString();
    }
}
