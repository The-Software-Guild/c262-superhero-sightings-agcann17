package com.sg.superhero.dao;

import com.sg.superhero.dto.*;

import java.util.List;

public interface SuperheroDao {

    //Person related methods
    public Person addPerson(Person person);
    public Person getPersonById(int id);
    public List<Person> getAllPeople();
    public List<Person> getAllPeopleByOrg(int orgId);
    public List<Person> getAllPeopleByLocation(int locId);
    public boolean updatePerson(Person person);
    public boolean deletePerson(Person person);


    //Org related methods
    public Org addOrg(Org org);
    public Org getOrgById(int id);
    public List<Org> getAllOrgs();
    public List<Org> getAllOrgByPerson(int personId);
    public boolean updateOrg(Org org);
    public boolean deleteOrg(Org org);


    //Location related methods
    public Location addLocation(Location loc);
    public Location getLocationById(int id);
    public List<Location> getAllLocations();
    public List<Location> getAllLocationsByPerson(int personId);
    public boolean updateLocation(Location loc);
    public boolean deleteLocation(Location loc);


    //Superpower related methods
    public Superpower addSuperpower(Superpower sp);
    public Superpower getSuperpowerById(int id);
    public List<Superpower> getAllSuperpowers();
    public boolean updateSuperpower(Superpower sp);
    public boolean deleteSuperpower(Superpower sp);


    //Sighting related methods
    public Sighting addSighting(Sighting sighting);
    public Sighting getSightingById(int id);
    public List<Sighting> getAllSightings();
    public boolean updateSighting(Sighting sighting);
    public boolean deleteSighting(Sighting sighting);

}
