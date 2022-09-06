package com.sg.superhero.dao;

import com.sg.superhero.dto.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts="/superhero_testDB_setup.sql")
public class SuperheroDaoDBImplTest {

    @Autowired
    SuperheroDaoDBImpl dao;


    // PERSON TEST
    @Test
    void testAddPerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);

        Person fromDao = dao.addPerson(person);
        person.setId(1);

        assertEquals(person, fromDao);

    }

    @Test
    void testGetPerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Person person2 = new Person();
        person2.setName("Pac Man");
        person2.setDescription("Nom Nom");
        person2.setVillainHeroId(1);
        person2.setSuperpowerId(1);
        person2 = dao.addPerson(person2);

        Person fromDao = dao.getPersonById(2);

        assertEquals(person2, fromDao);
    }

    @Test
    void testGetAllPeople(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Person person2 = new Person();
        person2.setName("Pac Man");
        person2.setDescription("Nom Nom");
        person2.setVillainHeroId(1);
        person2.setSuperpowerId(1);
        person2 = dao.addPerson(person2);

        List<Person> fromDao = dao.getAllPeople();

        assertEquals(2, fromDao.size());
        assertEquals(person2, fromDao.get(1));
    }

    @Test
    void testGetAllPeopleByOrg(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        person = dao.addPerson(person);


        Org org = new Org();
        org.setName("Justice League");
        org.setDescription("A Lot of heroes.");
        org.setLocationId(1);
        org = dao.addOrg(org);

        Member m = new Member();
        m.setOrgId(org.getId());
        m.setPersonId(person.getId());
        dao.addMember(m);
        List<Person> list = dao.getAllPeopleByOrg(2);

        assertEquals(1, list.size());
        assertEquals(person, list.get(0));
    }

    @Test
    void testGetAllPeopleByLocation(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        s.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        List<Person> list = dao.getAllPeopleByLocation(2);
        assertEquals(1, list.size());
        assertEquals(person, list.get(0));
    }

    @Test
    void testUpdatePerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        person.setName("Pac Man");
        person.setDescription("Nom Nom");
        assertTrue(dao.updatePerson(person));

        Person fromDao = dao.getPersonById(1);

        assertEquals(person, fromDao);
    }

    @Test
    void testDeletePerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        assertTrue(dao.deletePerson(person));

        List<Person> people = dao.getAllPeople();

        assertEquals(0, people.size());
    }


    //ORG TEST
    @Test
    void testAddOrg(){
        Org org = new Org();
        org.setName("League of Villains");
        org.setDescription("MHA");
        org.setLocationId(1);

        Org fromDao = dao.addOrg(org);
        org.setId(2);
        assertEquals(org, fromDao);
    }

    @Test
    void testGetOrgById(){
        Org org = new Org();
        org.setName("League of Villains");
        org.setDescription("MHA");
        org.setLocationId(1);
        dao.addOrg(org);
        org.setId(2);

        Org fromDao = dao.getOrgById(2);
        assertEquals(org, fromDao);
    }

    @Test
    void testGetAllOrgs(){
        Org org = new Org();
        org.setName("League of Villains");
        org.setDescription("MHA");
        org.setLocationId(1);
        dao.addOrg(org);
        org.setId(2);

        List<Org> orgs = dao.getAllOrgs();
        assertEquals(2, orgs.size());
        assertEquals(org, orgs.get(1));
    }

    @Test
    void testGetAllOrgByPerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        person = dao.addPerson(person);


        Org org = new Org();
        org.setName("Justice League");
        org.setDescription("A Lot of heroes.");
        org.setLocationId(1);
        org = dao.addOrg(org);

        Member m = new Member();
        m.setOrgId(org.getId());
        m.setPersonId(person.getId());
        dao.addMember(m);
        List<Org> list = dao.getAllOrgByPerson(1);

        assertEquals(1, list.size());
        assertEquals(org, list.get(0));
    }

    @Test
    void testUpdateOrg(){
        Org org = new Org();
        org.setName("League of Villains");
        org.setDescription("MHA");
        org.setLocationId(1);
        dao.addOrg(org);
        org.setId(2);

        org.setName("Avengers");
        org.setDescription("Marvel");
        assertTrue(dao.updateOrg(org));

        Org fromDao = dao.getOrgById(2);
        assertEquals(org, fromDao);
    }

    @Test
    void testDeleteOrg(){
        Org org = new Org();
        org.setName("League of Villains");
        org.setDescription("MHA");
        org.setLocationId(1);
        dao.addOrg(org);
        org.setId(2);

        assertTrue(dao.deleteOrg(org));

        List<Org> orgs = dao.getAllOrgs();

        assertEquals(1, orgs.size());
    }


    //LOCATION TEST
    @Test
    void testAddLocation(){
        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        Location fromDao = dao.addLocation(loc);
        loc.setId(2);

        assertEquals(loc, fromDao);
    }

    @Test
    void testGetLocationById(){
        Location loc = new Location();
        loc.setLatitude(3.333);
        loc.setLongitude(4.444);
        dao.addLocation(loc);
        loc.setId(2);

        Location fromDao = dao.getLocationById(2);

        assertEquals(loc, fromDao);
    }

    @Test
    void testGetAllLocations(){
        Location loc = new Location();
        loc.setLatitude(3.333);
        loc.setLongitude(4.444);
        dao.addLocation(loc);
        loc.setId(2);

        List<Location> locations = dao.getAllLocations();

        assertEquals(2, locations.size());
        assertEquals(loc, locations.get(1));
    }

    @Test
    void testGetAllLocationsByPerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude(3.333);
        loc.setLongitude(4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        s.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        List<Location> list = dao.getAllLocationsByPerson(1);
        assertEquals(1, list.size());
        assertEquals(loc, list.get(0));
    }

    @Test
    void testUpdateLocation(){
        Location loc = new Location();
        loc.setLatitude(3.333);
        loc.setLongitude(4.444);
        dao.addLocation(loc);
        loc.setId(2);

        loc.setLatitude(5.555);
        loc.setLongitude(6.666);
        assertTrue(dao.updateLocation(loc));

        Location fromDao = dao.getLocationById(2);
        assertEquals(loc, fromDao);
    }

    @Test
    void testDeleteLocation(){
        Location loc = new Location();
        loc.setLatitude(3.333);
        loc.setLongitude(4.444);
        dao.addLocation(loc);
        loc.setId(2);

        assertTrue(dao.deleteLocation(loc));
        List<Location> locations = dao.getAllLocations();

        assertEquals(1, locations.size());
    }


    //SUPERPOWER TEST
    @Test
    void testAddSuperpower(){
        Superpower sp = new Superpower();
        sp.setSuperpower("Super Speed");
        Superpower fromDao = dao.addSuperpower(sp);
        sp.setId(2);

        assertEquals(sp, fromDao);
    }

    @Test
    void testGetSuperpowerById(){
        Superpower sp = new Superpower();
        sp.setSuperpower("Super Speed");
        dao.addSuperpower(sp);
        sp.setId(2);

        Superpower fromDao = dao.getSuperpowerById(2);
        assertEquals(sp, fromDao);
    }

    @Test
    void testGetAllSuperpowers(){
        Superpower sp = new Superpower();
        sp.setSuperpower("Super Speed");
        dao.addSuperpower(sp);
        sp.setId(2);

        List<Superpower> powers = dao.getAllSuperpowers();
        assertEquals(2, powers.size());
        assertEquals(sp, powers.get(1));
    }

    @Test
    void testUpdateSuperpower(){
        Superpower sp = new Superpower();
        sp.setSuperpower("Super Speed");
        dao.addSuperpower(sp);
        sp.setId(2);

        sp.setSuperpower("All for One");
        assertTrue(dao.updateSuperpower(sp));

        Superpower fromDao = dao.getSuperpowerById(2);
        assertEquals(sp, fromDao);
    }

    @Test
    void testDeleteSuperpower(){
        Superpower sp = new Superpower();
        sp.setSuperpower("Super Speed");
        dao.addSuperpower(sp);
        sp.setId(2);

        assertTrue(dao.deleteSuperpower(sp));

        List<Superpower> powers = dao.getAllSuperpowers();
        assertEquals(1, powers.size());
    }



    //SIGHTING TEST
    @Test
    void testAddSighting(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        s.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        s.setPersonId(1);
        s.setLocationId(2);
        Sighting fromDao = dao.addSighting(s);

        assertEquals(s, fromDao);
    }

    @Test
    void testGetSightingById(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        s.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        Sighting fromDao = dao.getSightingById(1, 2);
        assertEquals(s, fromDao);
    }

    @Test
    void testGetAllSightings(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        s.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        List<Sighting> list = dao.getAllSightings();
        assertEquals(1, list.size());
        assertEquals(s, list.get(0));
    }

    @Test
    void testUpdateSighting(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Person p2 = new Person();
        p2.setName("TEST");
        p2.setDescription("TEST");
        p2.setVillainHeroId(1);
        p2.setSuperpowerId(1);
        dao.addPerson(p2);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        LocalDateTime dt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        s.setDate(dt);
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        Sighting s2 = new Sighting();
        s2.setDate(dt);
        s2.setPersonId(2);
        s2.setLocationId(1);
        dao.addSighting(s2);

        //Sighting fromDao2 = dao.getSightingById(1, 2);

        s.setDate(s.getDate().plusYears(1));
        assertTrue(dao.updateSighting(s));


        Sighting fromDao = dao.getSightingById(1, 2);
        List<Sighting> list = dao.getAllSightings();

        assertEquals(s, fromDao);
    }

    @Test
    void testDeleteSighting(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Location loc = new Location();
        loc.setLatitude((float)3.333);
        loc.setLongitude((float)4.444);
        dao.addLocation(loc);

        Sighting s = new Sighting();
        LocalDateTime dt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        s.setDate(dt);
        s.setPersonId(1);
        s.setLocationId(2);
        dao.addSighting(s);

        assertTrue(dao.deleteSighting(s));
        List<Sighting> list = dao.getAllSightings();

        assertEquals(0, list.size());

    }


    //MEMBER TEST
    @Test
    void testAddMember(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        person = dao.addPerson(person);


        Org org = new Org();
        org.setName("Justice League");
        org.setDescription("A Lot of heroes.");
        org.setLocationId(1);
        org = dao.addOrg(org);

        Member m = new Member();
        m.setOrgId(org.getId());
        m.setPersonId(person.getId());
        Member fromDao = dao.addMember(m);

        assertEquals(m, fromDao);

    }

    @Test
    void testGetAllMembers(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        person = dao.addPerson(person);


        Org org = new Org();
        org.setName("Justice League");
        org.setDescription("A Lot of heroes.");
        org.setLocationId(1);
        org = dao.addOrg(org);

        Member m = new Member();
        m.setOrgId(org.getId());
        m.setPersonId(person.getId());
        dao.addMember(m);
        List<Member> list = dao.getAllMembers();

        assertEquals(1, list.size());
        assertEquals(m, list.get(0));
    }

    @Test
    void testDeleteMember(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHeroId(1);
        person.setSuperpowerId(1);
        person = dao.addPerson(person);


        Org org = new Org();
        org.setName("Justice League");
        org.setDescription("A Lot of heroes.");
        org.setLocationId(1);
        org = dao.addOrg(org);

        Member m = new Member();
        m.setOrgId(org.getId());
        m.setPersonId(person.getId());
        dao.addMember(m);

        dao.deleteMember(m);
        List<Member> list = dao.getAllMembers();

        assertEquals(0, list.size());

    }

}
