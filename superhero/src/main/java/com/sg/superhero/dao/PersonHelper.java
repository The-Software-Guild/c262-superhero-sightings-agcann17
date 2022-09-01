package com.sg.superhero.dao;

import com.sg.superhero.dto.Person;
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
            statement.setInt(4, person.getVillainHero());

            return statement;
        }, keyHolder);

        person.setId(keyHolder.getKey().intValue());

        return person;
    }

    public static Person getPersonById(int id, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT id, name, description, superpower, villainHero " +
                "FROM person WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new PersonMapper(), id);
    }

    public static List<Person> getAllPeople(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM person;";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public static List<Person> getAllPeopleByOrg(int orgId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT p.* FROM person p JOIN member m ON m.person = p.id " +
                "WHERE m.org = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), orgId);
    }

    public static List<Person> getAllPeopleByLocation(int locId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT p.* FROM person p JOIN sighting s ON s.person = p.id " +
                "WHERE s.location = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), locId);
    }

    public static boolean updatePerson(Person person, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE person SET name = ?, description = ?, " +
                "superpower = ?, villainHero = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                person.getName(),
                person.getDescription(),
                person.getSuperpowerId(),
                person.getVillainHero(),
                person.getId());

        return true;
    }

    public static boolean deletePerson(Person person, JdbcTemplate jdbcTemplate) {
        final String DELETE_PERSON_MEMBER = "DELETE m.* FROM members m "
                + "JOIN person p ON m.person = p.id WHERE p.id = ?";
        jdbcTemplate.update(DELETE_PERSON_MEMBER, person.getId());

        final String DELETE_PERSON_SIGHTING = "DELETE s.* FROM sighting s "
                + "JOIN person p ON s.person = p.id WHERE p.id = ?";
        jdbcTemplate.update(DELETE_PERSON_SIGHTING, person.getId());

        final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(DELETE_PERSON, person.getId());

        return true;
    }

    private static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int index) throws SQLException {
            Person round = new Person();
            round.setId(rs.getInt("id"));
            round.setName(rs.getString("name"));
            round.setDescription(rs.getString("description"));
            round.setVillainHero(rs.getInt("villainHero"));
            round.setSuperpowerId(rs.getInt("superpower"));

            return round;
        }
    }

}
