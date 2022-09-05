package com.sg.superhero.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {

    private int locationId;
    private int personId;
    private LocalDateTime date;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return locationId == sighting.locationId && personId == sighting.personId && Objects.equals(date, sighting.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, personId, date);
    }
}
