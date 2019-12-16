package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.AssignmentType;

import java.util.Objects;
import java.util.StringJoiner;

public class Assignment extends Entity {
    private String name;
    private AssignmentType type;
    private Training training;

    public Assignment() {
    }

    public Assignment(int id) {
        super(id);
    }

    public Assignment(Integer id, String name, AssignmentType type, Training training) {
        super(id);
        this.name = name;
        this.type = type;
        this.training = training;
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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assignment that = (Assignment) o;
        return type == that.type &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(training, that.training);
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (name == null ? 0 : name.hashCode());
        result += seed * type.hashCode();
        result += seed * (training == null ? 0 : training.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Assignment.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("type=" + type)
                .add("training=" + training)
                .toString();
    }
}
