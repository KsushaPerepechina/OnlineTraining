package by.epam.onlinetraining.entity;

import java.util.StringJoiner;

public class Topic extends Entity {
    private String name;

    public Topic() {
    }

    public Topic(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return name == topic.name || (name != null && name.equals(topic.name));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Topic.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .toString();
    }
}
