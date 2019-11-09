package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.TaskState;

import java.sql.Date;
import java.util.StringJoiner;

public class Task extends Entity {
    private String name;
    private Date assignment;
    private Date deadline;
    private TaskState state;

    public Task() {
    }

    public Task(int id, String name, Date assignment, Date deadline, TaskState state) {
        super(id);
        this.name = name;
        this.assignment = assignment;
        this.deadline = deadline;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAssignment() {
        return assignment;
    }

    public void setAssignment(Date assignment) {
        this.assignment = assignment;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return (name == task.name || (name != null && name.equals(task.name))) &&
                (assignment == task.assignment || (assignment != null && assignment.equals(task.assignment))) &&
                (deadline == task.deadline || (deadline != null && deadline.equals(task.deadline)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (name == null ? 0 : name.hashCode());
        result += seed * (assignment == null ? 0 : assignment.hashCode());
        result += seed * (deadline == null ? 0 : deadline.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("assignment=" + assignment)
                .add("deadline=" + deadline)
                .toString();
    }
}
