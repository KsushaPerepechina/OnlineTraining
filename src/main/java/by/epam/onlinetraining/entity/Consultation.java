package by.epam.onlinetraining.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.StringJoiner;

public class Consultation extends Entity {//TODO
    private int studentId;
    private int mentorId;
    private Date dateTime;
    private BigDecimal cost;
    private int mark;
    private int quality;
    private List<Assignment> assignments;

    public Consultation() {
    }

    public Consultation(Integer id, int studentId, int mentorId, Date dateTime, BigDecimal cost, int mark, int quality,
                        List<Assignment> assignments) {
        super(id);
        this.studentId = studentId;
        this.mentorId = mentorId;
        this.dateTime = dateTime;
        this.cost = cost;
        this.mark = mark;
        this.quality = quality;
        this.assignments = assignments;
    }

    public Consultation(Integer id, int studentId, int mentorId, Date dateTime, BigDecimal cost, int mark, int quality) {//TODO delete
        super(id);
        this.studentId = studentId;
        this.mentorId = mentorId;
        this.dateTime = dateTime;
        this.cost = cost;
        this.mark = mark;
        this.quality = quality;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
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
        return studentId == that.studentId && mentorId == that.mentorId &&
                mark == that.mark && quality == that.quality &&
                getId() != null && getId().equals(that.getId()) &&
                (dateTime == that.dateTime || (dateTime != null && dateTime.equals(that.dateTime))) &&
                (cost == that.cost || (cost != null && cost.equals(that.cost)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * studentId;
        result += seed * mentorId;
        result += seed * mark;
        result += seed * quality;
        result += seed * (dateTime == null ? 0 : dateTime.hashCode());
        result += seed * (cost == null ? 0 : cost.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Consultation.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("studentId=" + studentId)
                .add("mentorId=" + mentorId)
                .add("dateTime=" + dateTime)
                .add("cost=" + cost)
                .add("mark=" + mark)
                .add("quality=" + quality)
                .toString();
    }
}
