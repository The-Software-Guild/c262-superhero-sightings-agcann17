package com.sg.superhero.dto;

import java.util.Objects;

public class Org {

    private int id;
    private String name;
    private String description;
    private int locationId;

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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    //Two orgs ares the same if they have the same name and location
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Org org = (Org) o;
        return locationId == org.locationId && Objects.equals(name, org.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, locationId);
    }
}
