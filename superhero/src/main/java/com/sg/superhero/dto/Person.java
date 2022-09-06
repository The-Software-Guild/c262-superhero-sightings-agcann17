package com.sg.superhero.dto;

import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private String description;
    private int villainHeroId;
    private int superpowerId;
    private String superpower;
    private String villainHero;

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

    public int getVillainHeroId() {
        return villainHeroId;
    }

    public void setVillainHeroId(int villainHeroId) {
        this.villainHeroId = villainHeroId;
    }

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public String getVillainHero() {
        return villainHero;
    }

    public void setVillainHero(String villainHero) {
        this.villainHero = villainHero;
    }

    //Two heroes are equal if they have the same name and superpower
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return superpowerId == person.superpowerId && Objects.equals(name, person.name) && villainHeroId == person.villainHeroId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, superpowerId);
    }
}
