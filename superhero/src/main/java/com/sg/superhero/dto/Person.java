package com.sg.superhero.dto;

import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private String description;
    private String villainHero;
    private int superpowerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVillainHero() {
        return villainHero;
    }

    public void setVillainHero(String villainHero) {
        this.villainHero = villainHero;
    }

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    //Two heroes are equal if they have the same name and superpower
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return superpowerId == person.superpowerId && Objects.equals(name, person.name) && Objects.equals(villainHero, person.villainHero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, superpowerId);
    }
}
