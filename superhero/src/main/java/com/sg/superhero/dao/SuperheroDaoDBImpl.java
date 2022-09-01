package com.sg.superhero.dao;

import com.sg.superhero.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuperheroDaoDBImpl implements SuperheroDao{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SuperheroDaoDBImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

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
        return OrgHelper.addOrg(org, jdbcTemplate);
    }

    @Override
    public Org getOrgById(int id) {
        return OrgHelper.getOrgById(id, jdbcTemplate);
    }

    @Override
    public List<Org> getAllOrgs() {
        return OrgHelper.getAllOrgs(jdbcTemplate);
    }

    @Override
    public List<Org> getAllOrgByPerson(int personId) {
        return OrgHelper.getAllOrgByPerson(personId, jdbcTemplate);
    }

    @Override
    public boolean updateOrg(Org org) {
        return OrgHelper.updateOrg(org, jdbcTemplate);
    }

    @Override
    public boolean deleteOrg(Org org) {
        return OrgHelper.deleteOrg(org, jdbcTemplate);
    }


    //Location
    @Override
    public Location addLocation(Location loc) {
        return LocationHelper.addLocation(loc, jdbcTemplate);
    }

    @Override
    public Location getLocationById(int id) {
        return LocationHelper.getLocationById(id, jdbcTemplate);
    }

    @Override
    public List<Location> getAllLocations() {
        return LocationHelper.getAllLocations(jdbcTemplate);
    }

    @Override
    public List<Location> getAllLocationsByPerson(int personId) {
        return LocationHelper.getAllLocationsByPerson(personId, jdbcTemplate);
    }

    @Override
    public boolean updateLocation(Location loc) {
        return LocationHelper.updateLocation(loc, jdbcTemplate);
    }

    @Override
    public boolean deleteLocation(Location loc) {
        return LocationHelper.deleteLocation(loc, jdbcTemplate);
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

    @Override
    public Member addMember(Member member) {
        return null;
    }


    //Members
    @Override
    public Member getMemberById(int id) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

    @Override
    public boolean deleteMember(Member member) {
        return false;
    }
}
