package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.AssignmentType;
import by.epam.onlinetraining.entity.type.TaskState;

import java.sql.Date;
import java.util.StringJoiner;

public class Assignment extends Entity {
    private String name;
    private AssignmentType type;
    private int trainingId;

    public Assignment() {
    }

    public Assignment(Integer id, String name, AssignmentType type, int trainingId) {
        super(id);
        this.name = name;
        this.type = type;
        this.trainingId = trainingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssignmentType getType() {
        return type;
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assignment assignment = (Assignment) o;
        return trainingId == assignment.trainingId && type == assignment.type &&
                getId() != null && getId().equals(assignment.getId()) &&
                name != null && name.equals(assignment.name);
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (name == null ? 0 : name.hashCode());
        result += seed * type.hashCode();
        result += seed * trainingId;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Assignment.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("type=" + type)
                .add("trainingId=" + trainingId)
                .toString();
    }
}
