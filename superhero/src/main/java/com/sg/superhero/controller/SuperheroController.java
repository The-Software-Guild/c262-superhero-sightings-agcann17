package com.sg.superhero.controller;

import com.sg.superhero.dao.SuperheroDao;
import com.sg.superhero.dao.SuperheroDaoDBImpl;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Person;
import com.sg.superhero.dto.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//http://localhost:8080/superhero

@Controller
//@RequestMapping()
public class SuperheroController {

    //TODO Update Supers Add/Edit page Superpower to drop down

    @Autowired
    SuperheroDao dao;


    /**
     * SUPER PEOPLE CRUD
     */
    @GetMapping("supers")
    public String displaySupers(Model model){
        List<Person> people = dao.getAllPeople();
        model.addAttribute("people", people);
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


}
