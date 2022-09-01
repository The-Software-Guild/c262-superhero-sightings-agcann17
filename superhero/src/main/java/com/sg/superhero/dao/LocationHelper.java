package com.sg.superhero.dao;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class LocationHelper {

    public static Location addLocation(Location loc, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO location(address, description, latitude, longitude) " +
                "VALUES(?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, loc.getAddress());
            statement.setString(2, loc.getDescription());
            statement.setDouble(3, loc.getLatitude());
            statement.setDouble(4, loc.getLongitude());

            return statement;
        }, keyHolder);

        loc.setId(keyHolder.getKey().intValue());

        return loc;
    }

    public static Location getLocationById(int id, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT id, address, description, latitude, longitude " +
                "FROM location WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
    }

    public static List<Location> getAllLocations(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM location;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    public static List<Location> getAllLocationsByPerson(int personId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT l.* FROM location l JOIN sighting s ON s.location = l.id " +
                "WHERE s.person = ?;";
        return jdbcTemplate.query(sql, new LocationMapper(), personId);
    }

    public static boolean updateLocation(Location loc, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE location SET address = ?, description = ?, " +
                "longitude = ?, latitude = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                loc.getAddress(),
                loc.getDescription(),
                loc.getLongitude(),
                loc.getLatitude(),
                loc.getId());

        return true;
    }

    public static boolean deleteLocation(Location loc, JdbcTemplate jdbcTemplate) {
        final String DELETE_LOCATION_SIGHTING = "DELETE s.* FROM sighting s "
                + "JOIN location l ON s.location = l.id WHERE l.id = ?";
        jdbcTemplate.update(DELETE_LOCATION_SIGHTING, loc.getId());

        final String DELETE_LOCATION = "DELETE FROM location WHERE id = ?";
        jdbcTemplate.update(DELETE_LOCATION, loc.getId());

        return true;
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location loc = new Location();
            loc.setId(rs.getInt("id"));
            loc.setAddress(rs.getString("address"));
            loc.setDescription(rs.getString("description"));
            loc.setLongitude(rs.getDouble("longitude"));
            loc.setLatitude(rs.getDouble("latitude"));

            return loc;
        }
    }
}
