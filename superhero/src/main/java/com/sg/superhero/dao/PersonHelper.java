package com.sg.superhero.dao;

import com.sg.superhero.dto.Person;
import com.sg.superhero.dto.Superpower;
import com.sg.superhero.dto.VillainHero;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class PersonHelper {

    public static Person addPerson(Person person, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO person(name, description, superpower, villainHero) " +
                "VALUES(?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getName());
            statement.setString(2, person.getDescription());
            statement.setInt(3, person.getSuperpowerId());
            statement.setInt(4, person.getVillainHeroId());

            return statement;
        }, keyHolder);

        person.setId(keyHolder.getKey().intValue());

        return person;
    }

    public static Person getPersonById(int id, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT id, name, description, superpower, villainHero " +
                "FROM person WHERE id = ?;";

        Person person = jdbcTemplate.queryForObject(sql, new PersonMapper(), id);
        addPowerToPerson(person, jdbcTemplate);
        addLabelToPerson(person, jdbcTemplate);

        return person;
    }

    public static List<Person> getAllPeople(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM person;";
        List<Person> people = jdbcTemplate.query(sql, new PersonMapper());

        for(Person p : people){
            addPowerToPerson(p, jdbcTemplate);
            addLabelToPerson(p, jdbcTemplate);
        }

        return people;
    }

    public static List<Person> getAllPeopleByOrg(int orgId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT p.* FROM person p JOIN member m ON m.person = p.id " +
                "WHERE m.org = ?;";
        List<Person> people = jdbcTemplate.query(sql, new PersonMapper(), orgId);

        for(Person p : people){
            addPowerToPerson(p, jdbcTemplate);
            addLabelToPerson(p, jdbcTemplate);
        }

        return people;
    }

    public static List<Person> getAllPeopleByLocation(int locId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT p.* FROM person p JOIN sighting s ON s.person = p.id " +
                "WHERE s.location = ?;";
        List<Person> people = jdbcTemplate.query(sql, new PersonMapper(), locId);

        for(Person p : people){
            addPowerToPerson(p, jdbcTemplate);
            addLabelToPerson(p, jdbcTemplate);
        }

        return people;
    }

    public static boolean updatePerson(Person person, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE person SET name = ?, description = ?, " +
                "superpower = ?, villainHero = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                person.getName(),
                person.getDescription(),
                person.getSuperpowerId(),
                person.getVillainHeroId(),
                person.getId());

        return true;
    }

    public static boolean deletePerson(Person person, JdbcTemplate jdbcTemplate) {
        final String DELETE_PERSON_MEMBER = "DELETE m.* FROM member m "
                + "JOIN person p ON m.person = p.id WHERE p.id = ?";
        jdbcTemplate.update(DELETE_PERSON_MEMBER, person.getId());

        final String DELETE_PERSON_SIGHTING = "DELETE s.* FROM sighting s "
                + "JOIN person p ON s.person = p.id WHERE p.id = ?";
        jdbcTemplate.update(DELETE_PERSON_SIGHTING, person.getId());

        final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(DELETE_PERSON, person.getId());

        return true;
    }

    //Helper Methods
    private static void addPowerToPerson(Person person, JdbcTemplate jdbcTemplate){
        final String sql = "SELECT id, superpower FROM superpower WHERE id = ?;";
        Superpower sp = jdbcTemplate.queryForObject(sql, new SuperpowerHelper.SuperpowerMapper(), person.getSuperpowerId());

        person.setSuperpower(sp.getSuperpower());
    }
    private static void addLabelToPerson(Person person, JdbcTemplate jdbcTemplate){
        final String sql = "SELECT id, label FROM villainHero WHERE id = ?;";
        VillainHero vh = jdbcTemplate.queryForObject(sql, new VillainHeroMapper(), person.getVillainHeroId());

        person.setVillainHero(vh.getLabel());
    }


    private static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int index) throws SQLException {
            Person round = new Person();
            round.setId(rs.getInt("id"));
            round.setName(rs.getString("name"));
            round.setDescription(rs.getString("description"));
            round.setVillainHeroId(rs.getInt("villainHero"));
            round.setSuperpowerId(rs.getInt("superpower"));

            return round;
        }
    }

    private static final class VillainHeroMapper implements RowMapper<VillainHero> {

        @Override
        public VillainHero mapRow(ResultSet rs, int index) throws SQLException {
            VillainHero round = new VillainHero();
            round.setId(rs.getInt("id"));
            round.setLabel(rs.getString("label"));

            return round;
        }

    }

}
