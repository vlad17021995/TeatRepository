package com.vlad17021995m.android.listways.model;

import java.io.Serializable;

/**
 * Created by qwerty on 17.02.2017.
 */
public class Entity implements Serializable {
    protected long id;

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "номер " + id;
    }
}
