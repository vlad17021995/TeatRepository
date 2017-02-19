package com.vlad17021995m.android.listways.model;

import java.io.Serializable;

/**
 * Created by qwerty on 17.02.2017.
 */
public class City extends Entity implements Serializable {
    private int highlight;
    private String name;

    public City(long id, int highlight, String name) {
        super(id);
        this.highlight = highlight;
        this.name = name;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Название - " + name + " Освещенность - " + highlight;
    }
}
