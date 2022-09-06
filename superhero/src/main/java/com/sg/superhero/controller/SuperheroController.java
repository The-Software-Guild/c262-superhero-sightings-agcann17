package com.sg.superhero.controller;

import com.sg.superhero.dao.SuperheroDao;
import com.sg.superhero.dao.SuperheroDaoDBImpl;
import com.sg.superhero.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//http://localhost:8080/superhero

@Controller
//@RequestMapping()
public class SuperheroController {

    //TODO Update Supers Add/Edit page Superpower to drop down
    //TODO Update Org Add/Edit page Location Dropdown

    @Autowired
    SuperheroDao dao;


    /**
     * SUPER PEOPLE CRUD
     */
    @GetMapping("supers")
    public String displaySupers(Model model){
        List<Person> people = dao.getAllPeople();
        List<Superpower> powers = dao.getAllSuperpowers();
        model.addAttribute("people", people);
        model.addAttribute("powers", powers);
        return "supers";
    }

    @PostMapping("addSuper")
    public String addPerson(HttpServletRequest request) {
        String name = request.getParameter("name");
        int superpower = Integer.parseInt(request.getParameter("superpower"));
        int type = Integer.parseInt(request.getParameter("type"));
        String description = request.getParameter("description");

        Person person = new Person();
        person.setName(name);
        person.setDescription(description);
        person.setSuperpowerId(superpower);
        person.setVillainHeroId(type);

        dao.addPerson(person);

        return "redirect:/supers";
    }

    @GetMapping("deleteSuper")
    public String deletePerson(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Person p = dao.getPersonById(id);
        dao.deletePerson(p);

        return "redirect:/supers";
    }

    @GetMapping("editSuper")
    public String editPerson(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = dao.getPersonById(id);

        model.addAttribute("person", person);
        return "editSuper";
    }

    @PostMapping("editSuper")
    public String performEditPerson(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = dao.getPersonById(id);

        person.setName(request.getParameter("name"));
        person.setDescription(request.getParameter("description"));
        person.setVillainHeroId(Integer.parseInt(request.getParameter("type")));
        person.setSuperpowerId(Integer.parseInt(request.getParameter("superpower")));

        dao.updatePerson(person);

        return "redirect:/supers";
    }


    /**
     * SUPERPOWERS CRUD
     */
    @GetMapping("powers")
    public String displayPowers(Model model){
        List<Superpower> powers = dao.getAllSuperpowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {
        String superpower = request.getParameter("superpower");

        Superpower sp = new Superpower();
        sp.setSuperpower(superpower);

        dao.addSuperpower(sp);

        return "redirect:/powers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower sp = dao.getSuperpowerById(id);
        dao.deleteSuperpower(sp);

        return "redirect:/powers";
    }

    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower sp = dao.getSuperpowerById(id);

        model.addAttribute("power", sp);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower sp = dao.getSuperpowerById(id);

        sp.setSuperpower(request.getParameter("superpower"));

        dao.updateSuperpower(sp);

        return "redirect:/powers";
    }


    /**
     * Locations CRUD
     */
    @GetMapping("locations")
    public String displayLocations(Model model){
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        Double latitude = Double.parseDouble(request.getParameter("latitude"));
        Double longitude = Double.parseDouble(request.getParameter("longitude"));

        Location loc = new Location();
        loc.setAddress(address);
        loc.setDescription(description);
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);

        dao.addLocation(loc);

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location loc = dao.getLocationById(id);
        dao.deleteLocation(loc);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location loc = dao.getLocationById(id);

        model.addAttribute("location", loc);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location loc = dao.getLocationById(id);

        loc.setAddress(request.getParameter("address"));
        loc.setDescription(request.getParameter("description"));
        loc.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        loc.setLatitude(Double.parseDouble(request.getParameter("latitude")));

        dao.updateLocation(loc);

        return "redirect:/locations";
    }

    /**
     * Organization CRUD
     */
    @GetMapping("organizations")
    public String displayOrganization(Model model){
        List<Org> orgs = dao.getAllOrgs();
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("orgs", orgs);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int locationId = Integer.parseInt(request.getParameter("location"));

        Org org = new Org();
        org.setDescription(description);
        org.setName(name);
        org.setLocationId(locationId);

        dao.addOrg(org);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Org org = dao.getOrgById(id);
        dao.deleteOrg(org);

        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Org org = dao.getOrgById(id);

        model.addAttribute("org", org);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Org org = dao.getOrgById(id);

        org.setName(request.getParameter("name"));
        org.setDescription(request.getParameter("description"));
        org.setLocationId(Integer.parseInt(request.getParameter("location")));

        dao.updateOrg(org);

        return "redirect:/organizations";
    }

    /**
     * Sighting CRUD
     */
    @GetMapping("sightings")
    public String displaySighting(Model model){
        List<Sighting> sightings = dao.getAllSightings();
        List<Person> people = dao.getAllPeople();
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("people", people);
        model.addAttribute("locations", locations);

        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        int person = Integer.parseInt(request.getParameter("person"));
        int location = Integer.parseInt(request.getParameter("location"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
        LocalDateTime dateTime = date.atStartOfDay();

        Sighting sighting = new Sighting();
        sighting.setPersonId(person);
        sighting.setLocationId(location);
        sighting.setDate(dateTime);

        dao.addSighting(sighting);

        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int personId = Integer.parseInt(request.getParameter("personId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Sighting sighting = dao.getSightingById(personId, locationId);
        dao.deleteSighting(sighting);

        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        int personId = Integer.parseInt(request.getParameter("personId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));

        Sighting sighting = dao.getSightingById(personId, locationId);

        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) {
        int personId = Integer.parseInt(request.getParameter("personId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));

        Sighting sighting = dao.getSightingById(personId, locationId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
        LocalDateTime dateTime = date.atStartOfDay();
        sighting.setDate(dateTime);

        dao.updateSighting(sighting);

        return "redirect:/sightings";
    }


    /**
     * HOME PAGE
     */
    @GetMapping()
    public String displayIndex(Model model){
        List<Sighting> sightings = dao.getAllSightings();
        List<Person> people = dao.getAllPeople();
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("people", people);
        model.addAttribute("locations", locations);

        return "index";
    }

}
