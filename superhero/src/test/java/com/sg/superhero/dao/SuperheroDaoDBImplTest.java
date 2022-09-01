package com.sg.superhero.dao;

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


}
