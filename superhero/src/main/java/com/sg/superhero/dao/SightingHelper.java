package com.sg.superhero.dao;

import com.sg.superhero.dto.Person;
import com.sg.superhero.dto.Sighting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class SightingHelper {


    public static Sighting addSighting(Sighting sighting, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO sighting(person, location, date) " +
                "VALUES(?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sighting.getPersonId());
            statement.setInt(2, sighting.getLocationId());
            statement.setTimestamp(3, Timestamp.valueOf(sighting.getDate()));

            return statement;
        }, keyHolder);

        return sighting;
    }

    public static Sighting getSightingById(int personId, int locId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT person, location, date " +
                "FROM sighting WHERE person = ? AND location = ?;";
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), personId, locId);
    }

    public static List<Sighting> getAllSightings(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM sighting;";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    public static boolean updateSighting(Sighting sighting, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE sighting SET date = ? WHERE person = ? AND location = ?";
        jdbcTemplate.update(sql,
                Timestamp.valueOf(sighting.getDate()),
                sighting.getPersonId(),
                sighting.getLocationId());

        return true;
    }

    public static boolean deleteSighting(Sighting sighting, JdbcTemplate jdbcTemplate) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE person = ? AND location = ?";
        jdbcTemplate.update(DELETE_SIGHTING, sighting.getPersonId(), sighting.getLocationId());

        return true;
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setPersonId(rs.getInt("person"));
            sighting.setLocationId(rs.getInt("location"));
            sighting.setDate(rs.getTimestamp("date").toLocalDateTime());


            return sighting;
        }
    }
}
