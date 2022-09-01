package com.sg.superhero.dao;

import com.sg.superhero.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SuperheroDaoDBImpl implements SuperheroDao{

    JdbcTemplate jdbcTemplate;

    //Person
    @Override
    public Person addPerson(Person person) {
        return PersonHelper.addPerson(person, jdbcTemplate);
    }

    @Override
    public Person getPersonById(int id) {
        return PersonHelper.getPersonById(id, jdbcTemplate);
    }

    @Override
    public List<Person> getAllPeople() {
        return PersonHelper.getAllPeople(jdbcTemplate);
    }

    @Override
    public List<Person> getAllPeopleByOrg(int orgId) {
        return PersonHelper.getAllPeopleByOrg(orgId, jdbcTemplate);
    }

    @Override
    public List<Person> getAllPeopleByLocation(int locId) {
        return PersonHelper.getAllPeopleByLocation(locId, jdbcTemplate);
    }

    @Override
    public boolean updatePerson(Person person) {
        return PersonHelper.updatePerson(person, jdbcTemplate);
    }

    @Override
    public boolean deletePerson(Person person) {
        return PersonHelper.deletePerson(person, jdbcTemplate);
    }


    //Org
    @Override
    public Org addOrg(Org org) {
        return null;
    }

    @Override
    public Org getOrgById(int id) {
        return null;
    }

    @Override
    public List<Org> getAllOrgs() {
        return null;
    }

    @Override
    public List<Org> getAllOrgByPerson(int personId) {
        return null;
    }

    @Override
    public boolean updateOrg(Org org) {
        return false;
    }

    @Override
    public boolean deleteOrg(Org org) {
        return false;
    }


    //Location
    @Override
    public Location addLocation(Location loc) {
        return null;
    }

    @Override
    public Location getLocationById(int id) {
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return null;
    }

    @Override
    public List<Location> getAllLocationsByPerson(int personId) {
        return null;
    }

    @Override
    public boolean updateLocation(Location loc) {
        return false;
    }

    @Override
    public boolean deleteLocation(Location loc) {
        return false;
    }


    //Superpower
    @Override
    public Superpower addSuperpower(Superpower sp) {
        return null;
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        return null;
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return null;
    }

    @Override
    public boolean updateSuperpower(Superpower sp) {
        return false;
    }

    @Override
    public boolean deleteSuperpower(Superpower sp) {
        return false;
    }


    //Sighting
    @Override
    public Sighting addSighting(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting getSightingById(int id) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return null;
    }

    @Override
    public boolean updateSighting(Sighting sighting) {
        return false;
    }

    @Override
    public boolean deleteSighting(Sighting sighting) {
        return false;
    }
}
