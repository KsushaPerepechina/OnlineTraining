package by.epam.onlinetraining.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private Integer id;

    public Entity() {
    }

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId () {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
}