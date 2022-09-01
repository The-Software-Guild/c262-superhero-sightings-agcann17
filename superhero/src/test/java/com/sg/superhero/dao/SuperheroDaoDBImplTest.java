package com.sg.superhero.dao;

import com.sg.superhero.dto.Org;
import com.sg.superhero.dto.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

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
        person.setVillainHero(1);
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
        person.setVillainHero(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Person person2 = new Person();
        person2.setName("Pac Man");
        person2.setDescription("Nom Nom");
        person2.setVillainHero(1);
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
        person.setVillainHero(1);
        person.setSuperpowerId(1);
        dao.addPerson(person);

        Person person2 = new Person();
        person2.setName("Pac Man");
        person2.setDescription("Nom Nom");
        person2.setVillainHero(1);
        person2.setSuperpowerId(1);
        person2 = dao.addPerson(person2);

        List<Person> fromDao = dao.getAllPeople();

        assertEquals(2, fromDao.size());
        assertEquals(person2, fromDao.get(1));
    }

    @Test
    void testGetAllPeopleByOrg(){
        //TODO once I have addMember method implemented
    }

    @Test
    void testGetAllPeopleByLocation(){
        //TODO once I have addLocation method implemented
    }

    @Test
    void testUpdatePerson(){
        Person person = new Person();
        person.setName("Super Man");
        person.setDescription("Clark Kent");
        person.setVillainHero(1);
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
        person.setVillainHero(1);
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
        //TODO implement after member methods
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


}
