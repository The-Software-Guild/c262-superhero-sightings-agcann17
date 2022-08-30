package com.sg.superhero.dto;

import java.util.Objects;

public class Superpower {

    private int id;
    private String superpower;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    //Two powers are equal if they have the same superpower
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superpower that = (Superpower) o;
        return Objects.equals(superpower, that.superpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, superpower);
    }
}
